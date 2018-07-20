package br.com.andreguedes.studentattendance.login

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.RootMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.andreguedes.studentattendance.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    private fun launchActivity() {
        activityTestRule.launchActivity(Intent())
    }

    private fun getActivity(): Activity {
        return activityTestRule.activity
    }

    @Test
    fun checkUsernameEdittextIsDisplayed() {
        launchActivity()
        onView(withId(R.id.edtUsername)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPasswordEdittextIsDisplayed() {
        launchActivity()
        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMessageIsDisplayedForEmptyData() {
        launchActivity()
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.msg_username_password_error))
                .inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoginSuccess() {
        launchActivity()
        onView(withId(R.id.edtUsername)).perform(
                typeText("aguedes"), closeSoftKeyboard())
        onView(withId(R.id.edtPassword)).perform(
                typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.msg_login_success))
                .inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoginError() {
        launchActivity()
        onView(withId(R.id.edtUsername)).perform(
                typeText("andre"), closeSoftKeyboard())
        onView(withId(R.id.edtPassword)).perform(
                typeText("654321"), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.msg_username_password_error))
                .inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkMessageForMaxAttempt() {
        launchActivity()
        for (i in 0..3) {
            onView(withId(R.id.edtUsername)).perform(clearText())
            onView(withId(R.id.edtPassword)).perform(clearText())
            onView(withId(R.id.edtUsername)).perform(
                    typeText("andre"), closeSoftKeyboard())
            onView(withId(R.id.edtPassword)).perform(
                    typeText("654321"), closeSoftKeyboard())
            onView(withId(R.id.btnLogin)).check(matches(isDisplayed())).perform(click())
        }
        onView(withText(R.string.msg_max_attempt_error))
                .inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
                .check(matches(isDisplayed()))
    }

}