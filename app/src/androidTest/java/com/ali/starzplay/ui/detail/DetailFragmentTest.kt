package com.ali.starzplay.ui.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ali.starzplay.R
import com.ali.starzplay.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testDetailFragmentDisplaysMediaInfo() {
        onView(withId(R.id.recycler_carousels))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.txt_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_detail_overview))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testPlayButtonVisibility() {
        onView(withId(R.id.recycler_carousels))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.btn_play))
            .check(matches(isDisplayed()))
    }
}
