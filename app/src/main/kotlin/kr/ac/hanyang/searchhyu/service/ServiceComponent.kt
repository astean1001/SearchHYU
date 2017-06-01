package kr.ac.hanyang.searchhyu.service

import dagger.Component
import kr.ac.hanyang.searchhyu.AppComponent
import kr.ac.hanyang.searchhyu.common.scope.ActivityScope


@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ServiceModule::class))
interface ServiceComponent {
    fun inject(target: ServiceActivity)
}
