package br.com.andreguedes.studentattendance.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.andreguedes.studentattendance.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenter(this)

        btnLogin.setOnClickListener {
            loginPresenter.doLogin(
                    edtUsername.text.toString(),
                    edtPassword.text.toString()
            )
        }
    }

    override fun showMessage(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

}