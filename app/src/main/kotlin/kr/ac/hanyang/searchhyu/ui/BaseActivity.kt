package kr.ac.hanyang.searchhyu.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kr.ac.hanyang.searchhyu.AppComponent
import kr.ac.hanyang.searchhyu.MyApplication

abstract class BaseActivity : AppCompatActivity() {
    var appComponent: AppComponent? = null
        private set
        get() = (application as MyApplication).appComponent

    abstract fun initComponent(savedInstanceState: Bundle?)
    abstract fun saveComponent(outState: Bundle)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveComponent(outState)
    }
}
