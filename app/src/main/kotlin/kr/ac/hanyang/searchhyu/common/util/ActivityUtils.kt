package kr.ac.hanyang.searchhyu.common.util

import android.content.Context
import android.widget.Toast

object ActivityUtils {
    fun showToast(c: Context, str: String) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }
}
