package com.example.espresso;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.object.HasToString.hasToString;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.espresso", appContext.getPackageName());
    }

    @Test
    public void textIsDisplayed() {
        onView(withText("Click Me!!"))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.message))
                .check(matches(isDisplayed()));
    }


    @Test
    public void listActivity() {
        onView(withId(R.id.launchButton))
                .perform(click());

        onView(withId(R.id.itemText))
                .perform(replaceText("Floppy"));


        onView(withId(R.id.addButton))
                .perform(click());

        Espresso.closeSoftKeyboard();

        onData(hasToString(containsString("Floppy")))
                .inAdapterView(withId(R.id.listView)).check(matches(isDisplayed()));
    }


    @Test
    public void complicatedListActivity() {
        onView(withId(R.id.launchButton))
                .perform(click());

        onView(withId(R.id.nameText))
                .perform(replaceText("Floppy"));


        onView(withId(R.id.descriptionText))
                .perform(replaceText("Description nnnn"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addButton))
                .perform(click());


        onData(withValue("Floppy"))
                .inAdapterView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    public static Matcher<Object> withValue(final String value) {
        return new BoundedMatcher<Object, Item>(Item.class) {
            @Override public void describeTo(Description description) {
                description.appendText("has value " + value);
            }
            @Override public boolean matchesSafely(Item item) {
                return item.getName().contentEquals(String.valueOf(value));
            }
        };
    }
}
