package kr.ac.hanyang.searchhyu.ui.main

import dagger.Module
import dagger.Provides
import kr.ac.hanyang.searchhyu.common.scope.ActivityScope

@Module
class MainPresenterModule {

    @Provides
    @ActivityScope
    fun providePresenter(presenter: MainPresenter): MainContract.Presenter {
        return presenter
    }
}
