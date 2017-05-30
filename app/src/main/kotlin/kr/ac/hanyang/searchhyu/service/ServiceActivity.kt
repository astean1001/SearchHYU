package kr.ac.hanyang.searchhyu.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kr.ac.hanyang.searchhyu.common.util.ActivityUtils

class ServiceActivity : AppCompatActivity() {
    private val TAG = ServiceActivity::class.java.simpleName

    private val REQ_CODE_SPEECH_INPUT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityUtils.startSpeechInputActivity(this, REQ_CODE_SPEECH_INPUT)
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

        }

        finish()
    }
}