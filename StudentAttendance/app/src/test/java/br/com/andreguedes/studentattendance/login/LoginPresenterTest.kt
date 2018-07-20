package br.com.andreguedes.studentattendance.login

import org.junit.Assert.*
import org.junit.Test

class LoginPresenterTest {

    @Test
    fun checkIfLoginAttemptIsExceeded() {
        val loginPresenter = LoginPresenter()
        assertEquals(1, loginPresenter.incrementLoginAttempt())
        assertEquals(2, loginPresenter.incrementLoginAttempt())
        assertEquals(3, loginPresenter.incrementLoginAttempt())

        assertTrue(loginPresenter.isLoginAttemptExceeded())
    }

    @Test
    fun checkUsernameAndPasswordIsCorrect() {
        val loginPresenter = LoginPresenter()
        assertTrue(loginPresenter.isLoginSuccess("aguedes", "123456"))
    }

}