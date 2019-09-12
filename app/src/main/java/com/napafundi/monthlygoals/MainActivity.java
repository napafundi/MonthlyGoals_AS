package com.napafundi.monthlygoals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_goal:
                final EditText taskEditText = new EditText(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Start AddGoalActivity
     * @param item The menu-item with a connected onClick listener (main_menu.xml.action_add_goal in this case)
     */
    public void showAddGoalForm(MenuItem item) {
        startActivity(new Intent(MainActivity.this, AddGoalActivity.class));
    }
}
