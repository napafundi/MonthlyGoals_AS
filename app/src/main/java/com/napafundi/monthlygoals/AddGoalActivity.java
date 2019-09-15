package com.napafundi.monthlygoals;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
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
    public AlertDialog addGoal(View view) {
        final EditText titleField = findViewById(R.id.EditTextMonthlyGoalTitle);
        String title = titleField.getText().toString();

        final DatePicker dateField = findViewById(R.id.DatePickerMonthlyGoalDate);
        int day = dateField.getDayOfMonth();
        int month = dateField.getMonth();
        int year = dateField.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        final EditText descField = findViewById(R.id.EditTextMonthlyGoalDescription);
        String desc = descField.getText().toString();
    }

    public void cancelAddGoal(View view) {
        finish();
    }

    private AlertDialog addGoalDialogBuilder(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setTitle(title);

        return builder.create();
    }
}
