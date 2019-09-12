package com.napafundi.monthlygoals;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityTestAddAction = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void changeViewTo_AddGoalForm_OnAction_AddGoalClick() {
        onView(withId(R.id.action_add_goal)).perform(click());
        intended(hasComponent(hasShortClassName(".AddGoalActivity")));
    }
}
