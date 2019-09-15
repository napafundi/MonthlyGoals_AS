package com.napafundi.monthlygoals;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the action bar ( wasn't working when changed through theme/styling in manifest and layout files)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.add_goal_form);
    }

    public void cancelAddGoal(View view) {
        finish();
    }
}
