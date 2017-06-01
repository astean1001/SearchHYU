package kr.ac.hanyang.searchhyu.ui.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import io.nlopez.smartlocation.SmartLocation
import kr.ac.hanyang.searchhyu.R
import kr.ac.hanyang.searchhyu.common.util.NetworkUtils
import kr.ac.hanyang.searchhyu.common.util.ParseUtils
import kr.ac.hanyang.searchhyu.common.util.TmapUtils
import java.util.*
import javax.inject.Inject


class MainPresenter @Inject constructor(val context: Context) : MainContract.Presenter {
    private val REQ_CODE_SPEECH_INPUT = 1
    private val REQ_PERMISSIONS_ACCESS_FINE_LOCATION = 2

    private var currentLat: Double = .0
    private var currentLng: Double = .0

    var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun start() {
        if (!NetworkUtils.isConnected(context)) {
            view?.requiredNetwork()
        }
    }

    override fun stop() {}

    override fun search(keyword: String) {
        view?.context?.let {
            TmapUtils.searchTmap(it, keyword)
        }
    }

    override fun speechInput() {
        view?.context?.let {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, it.getString(R.string.speech_recognizer_prompt))

            if (it is Activity) {
                it.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
            }
        }
    }

    override fun speechInputResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != AppCompatActivity.RESULT_OK)
            return

        val keyword = when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if(data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) == null)
                    return
                findLocation()
                ParseUtils.parseKeywords(data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)!!,currentLat,currentLng, context)
            }
            else -> null
        }

        keyword?.let {
            view?.showSearchResult(it)
        }
    }

    override fun moveToTMap() {
        view?.context?.let {
            TmapUtils.moveToTmap(it)
        }
    }

    override fun findLocation() {
        view?.context?.let {
            val locationControl = SmartLocation.with(it).location()

            locationControl.lastLocation?.let {
                view?.showLocation(it.latitude, it.longitude)
                currentLat = it.latitude
                currentLng = it.longitude
            }

            locationControl.oneFix().start {
                view?.showLocation(it.latitude, it.longitude)
                currentLat = it.latitude
                currentLng = it.longitude
            }
        }
    }

    override fun checkLocationPermission() {
        view?.context?.let {
            if (it is Activity) {
                if (ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(it,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQ_PERMISSIONS_ACCESS_FINE_LOCATION)
                } else {
                    findLocation()
                }
            }
        }
    }

    override fun permissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQ_PERMISSIONS_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findLocation()
                }
            }
        }
    }
}
