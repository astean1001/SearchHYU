package kr.ac.hanyang.searchhyu.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kr.ac.hanyang.searchhyu.R
import kr.ac.hanyang.searchhyu.common.util.ComponentManager
import kr.ac.hanyang.searchhyu.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainComponent>(), NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    //region BaseActivity
    override fun createComponent(): MainComponent {
        return DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainPresenterModule(MainPresenterModule())
                .build()
    }
    //endregion

    //region Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        init()
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
        presenter.stop()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region NavigationView.OnNavigationItemSelectedListener
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_navigation -> print("nav_navigation")
            R.id.nav_rest_area_by_route -> print("nav_rest_area_by_route")
            R.id.nav_theme_rest_area -> print("nav_theme_rest_area")
            R.id.nav_notice -> print("nav_notice")
            R.id.nav_settings -> print("nav_settings")
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    //endregion

    //region MainContract.View
    override fun showProgress() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(e: Throwable) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion

    //region Private methods
    private fun init() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        searchView.attachNavigationDrawerToMenuButton(drawerLayout)
        navView.setNavigationItemSelectedListener(this)

        tMapView.setSKPMapApiKey(getString(R.string.app_key))
    }
    //endregion
}
