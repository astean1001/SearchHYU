package kr.ac.hanyang.searchhyu.domain.network

import io.reactivex.Observable
import kr.ac.hanyang.searchhyu.domain.model.SearchPoiInfoRoot
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val APP_KEY = "appKey: 89cb9193-48b5-34dc-b13f-5cfea8eafeee"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(APP_KEY, ACCEPT)
    @GET("pois?version=1&resCoordType=WGS84GEO&reqCoordType=WGS84GEO")
    fun getPois(@Query("searchKeyword") keyword: String,
                @Query("centerLon") centerLong: Double?, @Query("centerLat") centerLat: Double?):
            Observable<SearchPoiInfoRoot>
}