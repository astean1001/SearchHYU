package kr.ac.hanyang.searchhyu

import android.app.Application
import com.skp.Tmap.TMapTapi

class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

        TMapTapi(this).setSKPMapAuthentication(getString(R.string.app_key))
    }
}
