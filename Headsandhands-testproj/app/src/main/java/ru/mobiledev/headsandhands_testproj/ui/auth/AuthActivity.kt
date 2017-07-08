package ru.mobiledev.headsandhands_testproj.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.mobiledev.headsandhands_testproj.R
import ru.mobiledev.headsandhands_testproj.ui.base.BaseActivity

class AuthActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
