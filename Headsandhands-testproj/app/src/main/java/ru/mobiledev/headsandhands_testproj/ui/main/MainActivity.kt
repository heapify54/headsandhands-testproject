package ru.mobiledev.headsandhands_testproj.ui.main

import android.os.Bundle
import ru.mobiledev.headsandhands_testproj.R
import ru.mobiledev.headsandhands_testproj.ui.auth.AuthActivity
import ru.mobiledev.headsandhands_testproj.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAuth = findViewById(R.id.btnAuth)
        btnAuth.setOnClickListener {
            startActivity(AuthActivity.newIntent(this@MainActivity))
        }

        supportActionBar?.title = getString(R.string.main_title)
    }
}
