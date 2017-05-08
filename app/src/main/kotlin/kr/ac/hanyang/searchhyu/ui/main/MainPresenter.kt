package kr.ac.hanyang.searchhyu.ui.main

import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter {
    var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun start() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}