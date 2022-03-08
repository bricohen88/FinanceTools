package com.example.financialtools

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.financialtools.Interest.InterestAdapter
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.AllOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkInterest() {
        onView(withText("Compound Interest Calculator")).check(matches(isDisplayed()))
    }

    @Test
    fun checkInterestButton() {
        onView(withId(R.id.launch_interest_calc)).check(matches(isDisplayed()))
    }

    @Test
    fun checkInterestCalcHeader() {
        onView(withId(R.id.launch_interest_calc)).perform(click())
        onView(withId(R.id.interestRateInput)).perform(typeText("7"))
        onView(withId(R.id.principleInput)).perform(typeText("1000"))
        onView(withId(R.id.submit_interest)).perform(click())
        onView(withId(R.id.interest_result_text)).check(matches(withText("Investing £1000 at 7%")))
    }

    @Test
    fun checkInterestCalc() {
        onView(withId(R.id.launch_interest_calc)).perform(click())
        onView(withId(R.id.interestRateInput)).perform(typeText("7"))
        onView(withId(R.id.principleInput)).perform(typeText("1000"))
        onView(withId(R.id.submit_interest)).perform(click())
        onView(withId(R.id.interest_recycleView)).perform(scrollToPosition<InterestAdapter.InterestViewHolder>(49))
        onView(withText("49")).check(matches(hasSibling(withText("£30,570.47"))))

    }
}