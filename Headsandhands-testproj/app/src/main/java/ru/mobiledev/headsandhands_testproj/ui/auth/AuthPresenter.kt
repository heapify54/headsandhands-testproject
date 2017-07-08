package ru.mobiledev.headsandhands_testproj.ui.auth

import ru.mobiledev.headsandhands_testproj.data.model.toModel
import ru.mobiledev.headsandhands_testproj.data.remote.RestApi
import ru.mobiledev.headsandhands_testproj.data.remote.WeatherResponse
import ru.mobiledev.headsandhands_testproj.data.remote.responseTransformer
import ru.mobiledev.headsandhands_testproj.data.remote.services.WeatherService
import ru.mobiledev.headsandhands_testproj.data.remote.subscribeNetwork
import ru.mobiledev.headsandhands_testproj.ui.base.BasePresenter
import rx.schedulers.Schedulers

/**
 * Created by heapify on 08.07.2017.
 */
class AuthPresenter(view: AuthView): BasePresenter<AuthView>(view) {
    private val weatherService = RestApi.createService(WeatherService::class.java)

    fun enter(email: String, password: String) {
        view.showLoadingDialog()
        unsubscribeOnDestroy(weatherService.checkWeather("London")
                .subscribeOn(Schedulers.io())
                .compose(responseTransformer<WeatherResponse>())
                .map { it.toModel() }
                .doAfterTerminate { view.dismissLoadingDialog() }
                .subscribeNetwork({
                    view.onAuthSuccess(it)
                }, {
                    view.showError(it.message)
                })
        )
    }
}