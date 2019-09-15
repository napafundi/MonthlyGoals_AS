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
public class AddGoalActivityTest {

    @Rule
    public IntentsTestRule<AddGoalActivity> addGoalActivityTestCancel = new IntentsTestRule<>(AddGoalActivity.class);

    @Test
    public void changeViewTo_MainActivity_OnAction_CancelClick() {
        onView(withId(R.id.ButtonCancelGoal)).perform(click());
        intended(hasComponent(hasShortClassName(".MainActivity")));
    }
}
