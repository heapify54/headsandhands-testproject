package ru.mobiledev.headsandhands_testproj.ui.auth

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.mobiledev.headsandhands_testproj.R

/**
 * A placeholder fragment containing a simple view.
 */
class AuthFragment : Fragment(), AuthView {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEnter: Button

    private val presenter = AuthPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view?.findViewById(R.id.etEmail) as EditText
        etPassword = view.findViewById(R.id.etPassword) as EditText
        btnEnter = view.findViewById(R.id.btnEnter) as Button

        btnEnter.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val isTrueEmail = emailValidate(email)
            var isTruePassword = true

            if (!isTrueEmail) {
                Toast.makeText(activity, R.string.auth_invalid_email, Toast.LENGTH_LONG).show()
            }

            if (!isTruePasswordCount(password)) {
                isTruePassword = false
                Toast.makeText(activity, R.string.auth_invalid_password_count, Toast.LENGTH_LONG).show()
            }

            if (isTruePassword && !passwordValidate(password)) {
                isTruePassword = false
                Toast.makeText(activity, R.string.auth_invalid_password, Toast.LENGTH_LONG).show()
            }

            if (!(isTrueEmail && isTruePassword)) {
                return@setOnClickListener
            } else {
                presenter.enter(email, password)
            }
        }
    }

    fun emailValidate(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isTruePasswordCount(password: String): Boolean {
        return password.length >= 6
    }

    fun passwordValidate(password: String): Boolean {
        var containsDigit = false
        var containsLowercase = false
        var containsUppercase = false

        password.forEach {
            if (it.isDigit()) {
                containsDigit = true
            }

            if (it.isLowerCase()) {
                containsLowercase = true
            }

            if (it.isUpperCase()) {
                containsUppercase = true
            }
        }

        return containsDigit && containsLowercase && containsUppercase
    }

    override fun onAuthSuccess() {

    }

    override fun showLoadingDialog(message: String?) {

    }

    override fun dismissLoadingDialog() {

    }

    override fun showError(message: String?) {

    }
}
