package kr.ac.hanyang.searchhyu.service

import android.content.Context
import android.speech.RecognizerIntent
import kr.ac.hanyang.searchhyu.common.util.ParseUtils
import kr.ac.hanyang.searchhyu.domain.usecase.GetPois
import kr.ac.hanyang.searchhyu.domain.usecase.GetPois2
import kr.ac.hanyang.searchhyu.domain.usecase.GetPois2_Factory
import javax.inject.Inject

class ServicePresenter @Inject constructor(
        val getPois: GetPois,
        val getPois2: GetPois2) : ServiceContract.Presenter {

    override fun bindView(view: ServiceContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unbindView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startService(c: Context, keyword: String, lat: Double, lng: Double) {
        ParseUtils.parseKeywords(keyword, lat, lng, c, getPois, getPois2)
    }
}