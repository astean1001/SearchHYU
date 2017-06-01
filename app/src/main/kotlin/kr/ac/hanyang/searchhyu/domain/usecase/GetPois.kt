package kr.ac.hanyang.searchhyu.domain.usecase

import io.reactivex.Observable
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import kr.ac.hanyang.searchhyu.domain.model.SearchPoiInfoRoot
import kr.ac.hanyang.searchhyu.domain.network.ApiService
import javax.inject.Inject

class GetPois : UseCase<SearchPoiInfoRoot, GetPois.Companion.Params> {

    private val apiService: ApiService

    @Inject
    constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
                apiService: ApiService) : super(threadExecutor, postExecutionThread) {
        this.apiService = apiService
    }

    override fun buildUseCaseObservable(params: Params): Observable<SearchPoiInfoRoot> {
        return apiService.getPois(params.keyword, params.lat, params.lng)
    }

    companion object {
        data class Params(val keyword: String, val lat: Double, val lng: Double)
    }
}