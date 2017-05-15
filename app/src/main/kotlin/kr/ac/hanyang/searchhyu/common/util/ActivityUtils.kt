package kr.ac.hanyang.searchhyu.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.StringRes
import android.widget.Toast

@Suppress("unused")
object ActivityUtils {
    private val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.skt.tmap.ku"

    fun showToast(c: Context, str: String) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(c: Context, @StringRes str: Int) {
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show()
    }

    fun moveToTmap(c: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tmap://A1"))
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            intent.data = Uri.parse(PLAY_STORE_URL)
            c.startActivity(intent)
        }
    }

    fun searchTmap(c: Context, query: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tmap://search?name=$query"))
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            intent.data = Uri.parse(PLAY_STORE_URL)
            c.startActivity(intent)
        }
    }
}
