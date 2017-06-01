package kr.ac.hanyang.searchhyu.domain.usecase

import io.reactivex.Observable
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import kr.ac.hanyang.searchhyu.domain.model.RestThemeRoot
import kr.ac.hanyang.searchhyu.domain.network.ApiService
import javax.inject.Inject

class GetThemeList : UseCase<RestThemeRoot, GetThemeList.Companion.Params> {

    private val apiService: ApiService

    @Inject
    constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
                apiService: ApiService) : super(threadExecutor, postExecutionThread) {
        this.apiService = apiService
    }

    override fun buildUseCaseObservable(params: Params): Observable<RestThemeRoot> {
        return apiService.getThemeList(params.pageNo, params.numOfRows)
    }

    companion object {
        class Params(val pageNo: Int, val numOfRows: Int)
    }
}