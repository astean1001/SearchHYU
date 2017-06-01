package kr.ac.hanyang.searchhyu.common.util

import android.content.Context
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

object ImageUtils {
    val PIN_ICON_URL = "https://developers.skplanetx.com/upload/tmap/marker/pin_b_b_a.png"

    private var _picasso: Picasso? = null
    private lateinit var picasso: Picasso

    private fun getPicasso(c: Context): Picasso {
        if (_picasso == null) {
            _picasso = Picasso.with(c)
            picasso = _picasso!!
        }
        return picasso
    }

    fun setTarget(c: Context, url: String, target: Target) {
        getPicasso(c).load(url).into(target)
    }
}