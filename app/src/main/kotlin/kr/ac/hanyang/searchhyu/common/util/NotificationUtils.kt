package kr.ac.hanyang.searchhyu.common.util

import android.app.NotificationManager
import android.content.Context

object NotificationUtils {
    fun cancelNotification(c: Context, notificationId: Int) {
        val notificationMgr = c.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        notificationMgr.cancel(notificationId)
    }
}