package com.napafundi.monthlygoals;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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

    /**
     * Test goal title, date, and description to be non-empty and valid inputs before passing to
     * data model
     * @param view
     */
    public void addGoal(View view) {
        boolean error = false;

        final EditText titleField = findViewById(R.id.EditTextMonthlyGoalTitle);
        String title = titleField.getText().toString().trim();
        if (title.length() == 0 || title.length() > 30) {
            error = true;
            titleField.setError("Goal title must be non-empty and less than 30 characters long.");
        }

        final DatePicker dateField = findViewById(R.id.DatePickerMonthlyGoalDate);
        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth();
        int year = dateField.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        final EditText descField = findViewById(R.id.EditTextMonthlyGoalDescription);
        String desc = descField.getText().toString().trim();
        if (desc.length() == 0 || desc.length() > 60) {
            error = true;
            descField.setError("Goal description must be non-empty and less than 60 characters long.");
        }

        if (!error) {
            MonthlyDao monthlyDao = MonthlyGoalsDatabase.getInstance(this).monthlyDao();
            Monthly goal = new Monthly(title, cal, desc);
            monthlyDao.save(goal);
            finish();
        }
    }

    public void cancelAddGoal(View view) {
        finish();
    }
}
