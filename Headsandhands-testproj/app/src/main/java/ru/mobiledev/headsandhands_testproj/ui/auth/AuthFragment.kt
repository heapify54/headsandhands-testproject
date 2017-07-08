package ru.mobiledev.headsandhands_testproj.ui.auth

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import ru.mobiledev.headsandhands_testproj.R

/**
 * A placeholder fragment containing a simple view.
 */
class AuthFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view?.findViewById(R.id.etEmail) as EditText
        etPassword = view.findViewById(R.id.etPassword) as EditText
    }
}
