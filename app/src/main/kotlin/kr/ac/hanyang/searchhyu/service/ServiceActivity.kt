package kr.ac.hanyang.searchhyu.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import io.nlopez.smartlocation.SmartLocation
import kr.ac.hanyang.searchhyu.common.util.ActivityUtils
import kr.ac.hanyang.searchhyu.ui.BaseActivity
import javax.inject.Inject

class ServiceActivity : BaseActivity<ServiceComponent>() {
    private val TAG = ServiceActivity::class.java.simpleName

    private val REQ_CODE_SPEECH_INPUT = 1

    private var currentLat: Double = .0
    private var currentLng: Double = .0

    @Inject
    lateinit var presenter: ServiceContract.Presenter

    override fun createComponent(): ServiceComponent {
        return DaggerServiceComponent.builder()
                .appComponent(appComponent)
                .serviceModule(ServiceModule())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        ActivityUtils.startSpeechInputActivity(this, REQ_CODE_SPEECH_INPUT)
        findLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            finish()
            return
        }

        val keyword = when (requestCode) {
            REQ_CODE_SPEECH_INPUT ->
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
            else -> null
        }

        Log.d(TAG, "keyword: $keyword")

        keyword?.let {
            presenter.startService(this, it, currentLat, currentLng)
        }

        finish()
    }

    private fun findLocation() {
        val locationControl = SmartLocation.with(this).location()

        locationControl.lastLocation?.let {
            currentLat = it.latitude
            currentLng = it.longitude
        }

        locationControl.oneFix().start {
            currentLat = it.latitude
            currentLng = it.longitude
        }
    }
}