package kr.ac.hanyang.searchhyu.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kr.ac.hanyang.searchhyu.AppComponent
import kr.ac.hanyang.searchhyu.MyApplication
import kr.ac.hanyang.searchhyu.common.util.ComponentManager

abstract class BaseActivity<out C : Any> : AppCompatActivity() {
    val appComponent: AppComponent
        get() = (application as MyApplication).appComponent

    private lateinit var _component: C

    protected val component: C
        get() = _component

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
            _component = createComponent()
        } else {
            _component = ComponentManager.restoreComponent<C>(savedInstanceState)?.let { it }
                    ?: createComponent()
        }
    }

    private fun saveComponent(outState: Bundle) {
        ComponentManager.saveComponent(_component, outState)
    }
}
