package ru.mobiledev.headsandhands_testproj.ui.auth

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.mobiledev.headsandhands_testproj.R
import ru.mobiledev.headsandhands_testproj.data.model.Weather


/**
 * A placeholder fragment containing a simple view.
 */
class AuthFragment : Fragment(), AuthView {

    companion object {
        private const val TAG = "AuthFragment"
    }

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEnter: Button

    private var parentView: View? = null

    private val presenter = AuthPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentView = view

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
                hideSoftKeyboard()
                presenter.enter(email, password)
            }
        }
    }

    private fun emailValidate(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isTruePasswordCount(password: String): Boolean {
        return password.length >= 6
    }

    private fun passwordValidate(password: String): Boolean {
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

    override fun onAuthSuccess(weather: Weather?) {
        Log.w(TAG, "Mid. temperature: ${weather?.middleTemperature ?: 0}")

        val message = "Средняя температура: ${weather?.middleTemperature ?: 0}"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Snackbar.make(parentView!!, message, Snackbar.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun showLoadingDialog(message: String?) {
        Log.w(TAG, "showLoadingDialog")

        // TODO add loading dialog or progress bar
    }

    override fun dismissLoadingDialog() {
        Log.w(TAG, "dismissLoadingDialog")

        // TODO hide loading dialog
    }

    override fun showError(message: String?) {
        Log.w(TAG, message)
    }

    private fun hideSoftKeyboard() {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(parentView!!.windowToken, 0)
    }
}
