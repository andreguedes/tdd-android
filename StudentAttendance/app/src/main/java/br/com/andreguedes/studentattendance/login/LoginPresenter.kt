package br.com.andreguedes.studentattendance.login

class LoginPresenter {

    private val MAX_LOGIN_ATTEMPT = 3
    private var loginAttempt: Int = 0

    fun incrementLoginAttempt(): Int {
        loginAttempt += 1
        return loginAttempt
    }

    fun isLoginAttemptExceeded(): Boolean {
        return loginAttempt >= MAX_LOGIN_ATTEMPT
    }

    fun isLoginSuccess(username: String, password: String): Boolean {
        if (isLoginAttemptExceeded()) {
            return false
        }
        if (username.equals("aguedes") && password.equals("123456")) {
            return true
        }
        incrementLoginAttempt()
        return false
    }

}