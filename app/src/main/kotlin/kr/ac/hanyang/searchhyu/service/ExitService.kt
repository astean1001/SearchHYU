package kr.ac.hanyang.searchhyu.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kr.ac.hanyang.searchhyu.common.util.NotificationUtils

class ExitService : Service() {

    private val TAG = ExitService::class.java.simpleName

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "ExitService")

        NotificationUtils.cancelNotification(this, FloatingViewService.NOTIFICATION_ID)
        stopService(Intent(this, FloatingViewService::class.java))
        stopSelf()
    }
}