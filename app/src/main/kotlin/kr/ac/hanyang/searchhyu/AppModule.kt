package kr.ac.hanyang.searchhyu

import android.content.Context
import dagger.Module
import dagger.Provides
import kr.ac.hanyang.searchhyu.data.executor.JobExecutor
import kr.ac.hanyang.searchhyu.domain.executor.PostExecutionThread
import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import kr.ac.hanyang.searchhyu.ui.UIThread
import javax.inject.Singleton

@Module
class AppModule(private val app: MyApplication) {

    @Provides
    @Singleton
    fun provideContext() : Context {
        return app
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread : UIThread) : PostExecutionThread {
        return uiThread
    }
}
