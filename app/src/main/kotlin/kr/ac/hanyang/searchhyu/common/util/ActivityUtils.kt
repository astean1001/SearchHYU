package kr.ac.hanyang.searchhyu.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.StringRes
import android.widget.Toast

@Suppress("unused")
object ActivityUtils {

    fun showToast(c: Context, str: String) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(c: Context, @StringRes str: Int) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }
}
