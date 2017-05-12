package kr.ac.hanyang.searchhyu.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kr.ac.hanyang.searchhyu.AppComponent
import kr.ac.hanyang.searchhyu.MyApplication
import kr.ac.hanyang.searchhyu.common.util.ComponentManager

abstract class BaseActivity<C : Any> : AppCompatActivity() {
    var appComponent: AppComponent? = null
        private set
        get() = (application as MyApplication).appComponent

    protected lateinit var component: C

    abstract fun createComponent(): C

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveComponent(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent(savedInstanceState)
    }

    private fun initComponent(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            component = createComponent()
        } else {
            component = ComponentManager.restoreComponent<C>(savedInstanceState)?.let { it }
                    ?: createComponent()
        }
    }

    private fun saveComponent(outState: Bundle) {
        ComponentManager.saveComponent(component, outState)
    }
}
