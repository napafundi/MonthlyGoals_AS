package com.napafundi.monthlygoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MonthlyGoalsViewModel monthlyGoalsViewModel;
    private MonthlyGoalsAdapter monthlyGoalsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthlyGoalsAdapter = new MonthlyGoalsAdapter(this, this);

        monthlyGoalsViewModel = ViewModelProviders.of(this).get(MonthlyGoalsViewModel.class);
        monthlyGoalsViewModel.getAllGoals().observe(this, goals -> monthlyGoalsAdapter.setData(goals));

        RecyclerView recyclerView = findViewById(R.id.monthly_goals_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(monthlyGoalsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_goal) {
            final EditText taskEditText = new EditText(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Start AddGoalActivity
     * @param item The menu-item with a connected onClick listener (main_menu.xml.action_add_goal in this case)
     */
    public void showAddGoalForm(MenuItem item) {
        startActivity(new Intent(MainActivity.this, AddGoalActivity.class));
    }
}
