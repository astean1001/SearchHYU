package kr.ac.hanyang.searchhyu.ui.main

import dagger.Component
import kr.ac.hanyang.searchhyu.AppComponent
import kr.ac.hanyang.searchhyu.common.scope.ActivityScope

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainPresenterModule::class))
interface MainComponent {
    fun inject(target: MainActivity)
}
