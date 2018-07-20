package br.com.andreguedes.studentattendance.login

import br.com.andreguedes.studentattendance.R

class LoginPresenter(val view: LoginView) {

    private val MAX_LOGIN_ATTEMPT = 3
    private var loginAttempt: Int = 0

    fun incrementLoginAttempt(): Int {
        loginAttempt += 1
        return loginAttempt
    }

    fun isLoginAttemptExceeded(): Boolean {
        return loginAttempt >= MAX_LOGIN_ATTEMPT
    }

    fun doLogin(username: String, password: String) {
        if (isLoginAttemptExceeded()) {
            view.showMessage(R.string.msg_max_attempt_error)
            return
        }
        if (username == "aguedes" && password == "123456") {
            view.showMessage(R.string.msg_login_success)
            return
        }
        incrementLoginAttempt()
        view.showMessage(R.string.msg_username_password_error)
    }

}