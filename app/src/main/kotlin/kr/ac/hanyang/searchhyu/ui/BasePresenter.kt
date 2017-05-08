package kr.ac.hanyang.searchhyu.ui

interface BasePresenter<in T : BaseView> {
    fun bindView(view : T)
    fun unbindView()
    fun start()
    fun stop()
}