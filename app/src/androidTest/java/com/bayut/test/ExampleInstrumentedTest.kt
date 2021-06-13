package com.bayut.test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bayut.test.view.activities.MainActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bayut.test", appContext.packageName)
    }

    @Test
    fun test_Main_Activity_Products_List() {
        ActivityScenario.launch(MainActivity::class.java)

        Thread.sleep(10000)
        onView(withId(R.id.products_rv)).check(matches(isDisplayed())).perform()
        onView(withText("Glasses")).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.toolbar)).perform()
        Thread.sleep(1000)
        Espresso.pressBack()
        Thread.sleep(500)
        onView(withText("monitor")).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.toolbar)).perform()
        Thread.sleep(1000)
        Espresso.pressBack()
    }
}