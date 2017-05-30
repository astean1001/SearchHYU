package kr.ac.hanyang.searchhyu.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.support.v7.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import kr.ac.hanyang.searchhyu.R

class FloatingViewService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("RtlHardcoded", "InflateParams")
    override fun onCreate() {
        super.onCreate()

        val ctx = ContextThemeWrapper(this, R.style.AppTheme)
        floatingView = LayoutInflater.from(ctx).inflate(R.layout.layout_floating_widget, null)

        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 0
        params.y = 100

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.addView(floatingView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(floatingView)
    }
}