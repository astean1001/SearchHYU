package kr.ac.hanyang.searchhyu

import android.content.Context
import dagger.Component
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun getContext(): Context
    fun getThreadExecutor(): ThreadExecutor
    fun getPostExecutionThread(): PostExecutionThread
}
