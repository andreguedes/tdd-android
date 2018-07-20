package br.com.andreguedes.studentattendance.login

import br.com.andreguedes.studentattendance.R
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoginPresenterTest {

    private val loginView = mock(LoginView::class.java)

    @Test
    fun checkIfLoginAttemptIsExceeded() {
        val loginPresenter = LoginPresenter(loginView)
        assertEquals(1, loginPresenter.incrementLoginAttempt())
        assertEquals(2, loginPresenter.incrementLoginAttempt())
        assertEquals(3, loginPresenter.incrementLoginAttempt())
        assertTrue(loginPresenter.isLoginAttemptExceeded())
    }

    @Test
    fun checkIfLoginAttemptIsNotExceeded() {
        val loginPresenter = LoginPresenter(loginView)
        assertFalse(loginPresenter.isLoginAttemptExceeded())
    }

    @Test
    fun checkUsernameAndPasswordIsCorrect() {
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("aguedes", "123456")
        verify(loginView).showMessage(R.string.msg_login_success)
    }

    @Test
    fun checkUsernameAndPasswordIsIncorrect() {
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("andre", "654321")
        verify(loginView).showMessage(R.string.msg_username_password_error)
    }

    @Test
    fun checkIfLoginAttemptIsExceededAndViewMethodCalled() {
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("andre", "654321")
        loginPresenter.doLogin("andre", "654321")
        loginPresenter.doLogin("andre", "654321")
        loginPresenter.doLogin("andre", "654321")
        verify(loginView).showMessage(R.string.msg_max_attempt_error)
    }

}