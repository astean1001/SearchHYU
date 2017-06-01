package kr.ac.hanyang.searchhyu.service

import android.content.Context
import kr.ac.hanyang.searchhyu.ui.BasePresenter
import kr.ac.hanyang.searchhyu.ui.BaseView

/**
 * Created by sangwoo on 2017. 6. 1..
 */
interface ServiceContract {
    interface Presenter : BasePresenter<ServiceContract.View> {
        fun startService(c: Context, keyword: String, lat: Double, lng: Double)
    }
    interface  View : BaseView
}