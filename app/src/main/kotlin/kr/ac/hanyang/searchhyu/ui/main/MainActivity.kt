package kr.ac.hanyang.searchhyu.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kr.ac.hanyang.searchhyu.R
import kr.ac.hanyang.searchhyu.common.util.ActivityUtils
import kr.ac.hanyang.searchhyu.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainComponent>(), NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    override val context: Context
        get() = this

    @Suppress("unused")
    private val TAG = MainActivity::class.java.simpleName

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
        presenter.bindView(this)

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
        // as you specify a parent context in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.speechInputResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.permissionsResult(requestCode, permissions, grantResults)
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
    override fun showProgress() {}

    override fun hideProgress() {}

    override fun showError(e: Throwable) {}

    override fun requiredNetwork() {
        ActivityUtils.showToast(this, R.string.required_network)
    }

    override fun showSearchResult(result: String) {
        searchView.setSearchText(result)
    }

    override fun showLocation(latitude: Double, longitude: Double) {
        tMapView.setLocationPoint(longitude, latitude)
    }
    //endregion

    //region Private methods
    private fun init() {

        fab.setOnClickListener {
            presenter.speechInput()
        }

        searchView.attachNavigationDrawerToMenuButton(drawerLayout)
        navView.setNavigationItemSelectedListener(this)

        searchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSearchAction(currentQuery: String?) {
                presenter.search(currentQuery!!)
            }

            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {}
        })

        searchView.setOnMenuItemClickListener {
            presenter.search(searchView.query)
        }

        tMapView.setTrackingMode(true)
        tMapView.setIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_location))
        tMapView.setIconVisibility(true)

        presenter.checkLocationPermission()
    }
    //endregion
}
