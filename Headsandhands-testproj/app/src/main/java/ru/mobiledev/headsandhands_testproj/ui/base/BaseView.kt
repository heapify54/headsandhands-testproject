package ru.mobiledev.headsandhands_testproj.ui.base

/**
 * Created by heapify on 07.07.2017.
 */
interface BaseView {
    fun showLoadingDialog(message: String? = null)
    fun dismissLoadingDialog()
    fun showError(message: String?)
}