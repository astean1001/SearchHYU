package kr.ac.hanyang.searchhyu.domain.network

import io.reactivex.Observable
import kr.ac.hanyang.searchhyu.domain.model.RestThemeRoot
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val key = "6008226101"
        const val type = "json"
    }

    @GET("restThemeList?key=$key&type=$type")
    fun getThemeList(@Query("pageNo") pageNo: Int,
                     @Query("numOfRows") numOfRows: Int): Observable<RestThemeRoot>
}