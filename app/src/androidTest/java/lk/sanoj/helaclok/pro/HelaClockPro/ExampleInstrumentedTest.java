package lk.sanoj.helaclok.pro.HelaClockPro;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("lk.sanoj.helaclok.pro.HelaClockPro", appContext.getPackageName());
    }

@Test
public void changeTextColorDialog() {
    try (ActivityScenario<WidgetSetting> ignored = ActivityScenario.launch(WidgetSetting.class)) {
        // Click the button
        onView(withId(R.id.textcolour)).perform(click());

        // Check if the dialog is displayed
        onView(withId(R.id.colorPickerDialog))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }
}
}
