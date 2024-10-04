package com.ali.starzplay.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ali.starzplay.R
import com.ali.starzplay.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewItemClickNavigatesToDetailFragment() {
        Thread.sleep(2000)

        onView(withId(R.id.recycler_carousels))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.detailFragment))
            .check(matches(isDisplayed()))
    }
}

