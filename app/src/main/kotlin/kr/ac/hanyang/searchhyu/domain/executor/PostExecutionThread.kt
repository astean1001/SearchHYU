package kr.ac.hanyang.searchhyu.domain.executor

import io.reactivex.Scheduler

/**
 * Thread abstraction.
 */
interface PostExecutionThread {
    val scheduler : Scheduler
}
