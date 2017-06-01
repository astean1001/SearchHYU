package kr.ac.hanyang.searchhyu.service

import dagger.Module
import dagger.Provides
import kr.ac.hanyang.searchhyu.common.scope.ActivityScope

@Module
class ServiceModule {

    @Provides
    @ActivityScope
    fun providePresenter(presenter: ServicePresenter): ServiceContract.Presenter {
        return presenter
    }
}
