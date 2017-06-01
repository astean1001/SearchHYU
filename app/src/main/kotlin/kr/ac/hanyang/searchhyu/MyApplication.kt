package kr.ac.hanyang.searchhyu

import android.app.Application
import com.skp.Tmap.TMapTapi
import kr.ac.hanyang.searchhyu.data.network.ApiModule

class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule("http://data.ex.co.kr/openapi/restinfo/"))
                .build()

        TMapTapi(this).setSKPMapAuthentication(getString(R.string.app_key))
    }
}
