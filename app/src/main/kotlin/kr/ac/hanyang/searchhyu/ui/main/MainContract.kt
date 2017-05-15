package kr.ac.hanyang.searchhyu.ui.main

import kr.ac.hanyang.searchhyu.ui.BasePresenter
import kr.ac.hanyang.searchhyu.ui.BaseView

interface MainContract {
    interface Presenter : BasePresenter<View>
    interface View : BaseView {
        fun requiredNetwork()
    }
}
