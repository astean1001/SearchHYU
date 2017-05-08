package kr.ac.hanyang.searchhyu.domain.usecase

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor

abstract class UseCase<T, in Params>(val threadExecutor: ThreadExecutor,
                                     val postExecutionThread: PostExecutionThread) {

    private var disposable: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(params: Params): Disposable {
        val disposable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe()
        addDisposable(disposable)
        return disposable
    }

    fun execute(params: Params, onNext: Consumer<in T>): Disposable {
        val disposable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe(onNext)
        addDisposable(this.disposable)
        return disposable
    }

    fun execute(params: Params, onNext: Consumer<in T>, onError: Consumer<in Throwable>):
            Disposable {
        val disposable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe(onNext, onError)
        addDisposable(this.disposable)
        return disposable
    }

    fun execute(params: Params, onNext: Consumer<in T>, onError: Consumer<in Throwable>,
                onComplete: Action): Disposable {
        val disposable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe(onNext, onError, onComplete)
        addDisposable(this.disposable)
        return disposable
    }

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun clear() {
        if (disposable.isDisposed) {
            disposable = CompositeDisposable()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }
}
