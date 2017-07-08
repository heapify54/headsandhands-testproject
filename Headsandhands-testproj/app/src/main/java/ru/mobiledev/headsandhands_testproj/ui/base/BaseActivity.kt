package ru.mobiledev.headsandhands_testproj.ui.base

import android.content.Intent
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import ru.mobiledev.headsandhands_testproj.R

/**
 * Created by heapify on 07.07.2017.
 */
open class BaseActivity : AppCompatActivity(), BaseView {
    companion object {
        const val LOADING_DIALOG_TAG = "loading_dialog"

        private const val TAG = "BaseActivity"
    }

    private var toolbar: Toolbar? = null
    private var receivedIntent: Intent? = null

    private lateinit var dialog: DialogFragment

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        toolbar = findViewById(R.id.toolbar) as Toolbar?
        bindToolbar()
    }

    private fun bindToolbar() {
        if (toolbar == null) {
            return
        }

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
    }

    override fun showLoadingDialog(message: String?) {

    }

    override fun showError(message: String?) {

    }

    override fun dismissLoadingDialog() {

    }
}