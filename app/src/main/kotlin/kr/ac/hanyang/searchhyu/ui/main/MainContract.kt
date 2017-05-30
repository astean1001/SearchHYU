package kr.ac.hanyang.searchhyu.ui.main

import android.content.Context
import android.content.Intent
import kr.ac.hanyang.searchhyu.ui.BasePresenter
import kr.ac.hanyang.searchhyu.ui.BaseView

interface MainContract {
    interface Presenter : BasePresenter<View> {
        fun search(keyword: String)
        fun speechInput()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun moveToTMap()
        fun findLocation()
        fun checkLocationPermission()
        fun checkSystemAlertWindowPermission()
        fun permissionsResult(requestCode: Int, permissions: Array<out String>,
                              grantResults: IntArray)
    }

    interface View : BaseView {
        val context: Context
        fun requiredNetwork()
        fun showSearchResult(result: String)
        fun showLocation(latitude: Double, longitude: Double)
    }
}
