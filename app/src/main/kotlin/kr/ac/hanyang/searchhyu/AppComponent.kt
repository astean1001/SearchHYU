package kr.ac.hanyang.searchhyu

import android.content.Context
import dagger.Component
import kr.ac.hanyang.searchhyu.data.network.ApiModule
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import kr.ac.hanyang.searchhyu.domain.network.ApiService
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {
    fun getContext(): Context
    fun getThreadExecutor(): ThreadExecutor
    fun getPostExecutionThread(): PostExecutionThread
    fun getApiService(): ApiService
}
