package kr.ac.hanyang.searchhyu.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.support.v7.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import kr.ac.hanyang.searchhyu.R
import kr.ac.hanyang.searchhyu.common.util.ActivityUtils
import kr.ac.hanyang.searchhyu.ui.main.MainActivity

class FloatingViewService : Service(), View.OnTouchListener {

    @Suppress("unused")
    private val TAG = FloatingViewService::class.java.simpleName

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View
    private lateinit var params: WindowManager.LayoutParams

    private var initialX = 0
    private var initialY = 0

    private var initialTouchX = 0f
    private var initialTouchY = 0f

    private var dragging = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("RtlHardcoded", "InflateParams")
    override fun onCreate() {
        super.onCreate()

        val ctx = ContextThemeWrapper(this, R.style.AppTheme)
        floatingView = LayoutInflater.from(ctx).inflate(R.layout.layout_floating_widget, null)

        floatingView.findViewById(R.id.launchButton).setOnTouchListener(this)

        params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        params.x = 0
        params.y = 0

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.addView(floatingView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(floatingView)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = params.x
                initialY = params.y

                initialTouchX = event.rawX
                initialTouchY = event.rawY
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                dragging = true

                params.x = initialX + (event.rawX - initialTouchX).toInt()
                params.y = initialY + (event.rawY - initialTouchY).toInt()

                windowManager.updateViewLayout(floatingView, params)
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (!dragging) {
                    val intent = Intent(this, ServiceActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    return true
                } else {
                    dragging = false
                }
            }
        }

        return false
    }
}