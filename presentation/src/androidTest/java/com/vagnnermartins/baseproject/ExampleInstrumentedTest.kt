package com.vagnnermartins.baseproject

import android.app.PendingIntent.getActivity
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.vagnnermartins.baseproject.projects.ProjectsActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import com.vagnnermartins.baseproject.projects.ProjectListAdapter
import org.hamcrest.Matchers.*
import android.support.test.espresso.idling.CountingIdlingResource
import org.junit.After
import org.junit.Before


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val CONST_REPOSITORY_RIGHT_USERNAME = "vagnnermartins"
    val CONST_REPOSITORY_WRONG_USERNAME = "eunaoseiseexiste"

    @get:Rule
    var mActivityTestRule: ActivityTestRule<ProjectsActivity> = ActivityTestRule(ProjectsActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(mActivityTestRule.activity.espressoTestIdlingResource)
    }

    @After
    fun after(){
        IdlingRegistry.getInstance().unregister(mActivityTestRule.activity.espressoTestIdlingResource)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.vagnnermartins.baseproject", appContext.packageName)
    }

    @Test
    fun addExistingRepository(){
        onView(withId(R.id.projectsUser)).perform(click())
        Thread.sleep(150)
        onView(withId(R.id.dialog_user_username)).perform(typeText(CONST_REPOSITORY_RIGHT_USERNAME))
        onView(withText(mActivityTestRule.activity.resources.getString(R.string.dialog_user_positive_button)))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
        onView(withId(R.id.projectsRecyclerView))
                .perform(actionOnItemAtPosition<ProjectListAdapter.ViewHolder>(0, click()))
    }

    @Test
    fun addUnexistingRepository(){
        onView(withId(R.id.projectsUser)).perform(click())
        Thread.sleep(150)
        onView(withId(R.id.dialog_user_username)).perform(typeText(CONST_REPOSITORY_WRONG_USERNAME))
        onView(withText(mActivityTestRule.activity.resources.getString(R.string.dialog_user_positive_button)))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())
        onView(withText(R.string.no_projects)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.getWindow().getDecorView())))).check(matches(isDisplayed()))
    }
}
