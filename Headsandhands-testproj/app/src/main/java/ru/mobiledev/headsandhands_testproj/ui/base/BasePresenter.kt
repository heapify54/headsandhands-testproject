package ru.mobiledev.headsandhands_testproj.ui.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by heapify on 07.07.2017.
 */
open class BasePresenter<out T>(protected val view: T) where T : BaseView {
    protected val subscriptions = CompositeSubscription()

    init {

    }

    fun onDestroyView() {
        subscriptions.clear()
    }

    protected fun unsubscribeOnDestroy(subscription: Subscription) {
        subscriptions.add(subscription)
    }
}