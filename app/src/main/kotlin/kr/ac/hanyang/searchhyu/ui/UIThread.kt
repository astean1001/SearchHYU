package kr.ac.hanyang.searchhyu.ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread implementation of [PostExecutionThread]
 */
@Singleton
class UIThread @Inject constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
