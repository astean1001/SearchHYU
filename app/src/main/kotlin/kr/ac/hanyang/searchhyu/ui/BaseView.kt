package kr.ac.hanyang.searchhyu.ui

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(e: Throwable)
}
