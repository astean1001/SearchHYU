package kr.ac.hanyang.searchhyu.ui.main

import android.content.Context
import kr.ac.hanyang.searchhyu.common.util.NetworkUtils
import javax.inject.Inject

class MainPresenter @Inject constructor(val context: Context) : MainContract.Presenter {
    var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun start() {
        if (!NetworkUtils.isConnected(context)) {
            view?.requiredNetwork()
        }
    }

    override fun stop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
