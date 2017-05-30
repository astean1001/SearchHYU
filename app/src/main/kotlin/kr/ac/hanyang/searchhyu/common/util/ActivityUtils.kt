package kr.ac.hanyang.searchhyu.common.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.RecognizerIntent
import android.support.annotation.StringRes
import android.widget.Toast
import kr.ac.hanyang.searchhyu.R
import java.util.*

@Suppress("unused")
object ActivityUtils {

    fun showToast(c: Context, str: String) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(c: Context, @StringRes str: Int) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }

    fun startSpeechInputActivity(activity: Activity, requestCode: Int) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                activity.getString(R.string.speech_recognizer_prompt))

        activity.startActivityForResult(intent, requestCode)
    }
}
