package artalejo.com.epg

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import artalejo.com.epg.ui.splash.SplashActivity
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.hasToString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText


@RunWith(AndroidJUnit4::class)
class PopularShowsActivityTest {

    private val THIRD_ITEM_POS = 1
    private val VIKINGS_TITLE = "Vikings"
    private val COOL_STUFF_TITLE = "Cool Stuff"

    @Rule @JvmField  var splashRule: ActivityTestRule<SplashActivity> =
            ActivityTestRule(SplashActivity::class.java)

    @Test
    fun splashTest() {
        onData(hasToString(containsString(VIKINGS_TITLE)))
        onData(hasToString(containsString(COOL_STUFF_TITLE)))
        onView(withId(R.id.channels_recycler)).perform(ViewActions.swipeUp())
        onView(withId(R.id.channels_recycler)).perform(ViewActions.swipeDown())
        onView(withId(R.id.channels_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(THIRD_ITEM_POS, click()))
        onView(withId(R.id.channel_title)).check(matches(withText("History")))
        onView(withId(R.id.show_year)).check(matches(withText("2013")))
        onView(withId(R.id.show_title)).check(matches(withText("Vikings (Live)")))
    }

}