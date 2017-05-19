package kr.ac.hanyang.searchhyu.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object TmapUtils {
    private val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.skt.tmap.ku"

    fun moveToTmap(c: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tmap://A1"))
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            redirectPlayStore(c, intent)
        }
    }

    fun searchTmap(c: Context, query: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tmap://search?name=$query"))
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            redirectPlayStore(c, intent)
        }
    }

    fun routeTmap(c: Context, startX: Double, startY: Double, goalX: Double, goalY: Double) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tmap://route?startx=$startX" +
                "&starty=$startY&goalx=$goalX&goaly=$goalY"))
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            redirectPlayStore(c, intent)
        }
    }

    private fun redirectPlayStore(c: Context, intent: Intent) {
        intent.data = Uri.parse(PLAY_STORE_URL)
        c.startActivity(intent)
    }
}