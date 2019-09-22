package com.napafundi.monthlygoals;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MonthlyGoalsViewModel monthlyGoalsViewModel;
    private MonthlyGoalsAdapter monthlyGoalsAdapter;
    private RecyclerView recyclerView;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthlyGoalsAdapter = new MonthlyGoalsAdapter(this, this);

        monthlyGoalsViewModel = ViewModelProviders.of(this).get(MonthlyGoalsViewModel.class);
        monthlyGoalsViewModel.getAllGoals().observe(this, goals -> monthlyGoalsAdapter.setData(goals));

        recyclerView = findViewById(R.id.monthly_goals_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(monthlyGoalsAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(context, monthlyGoalsViewModel, monthlyGoalsAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

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

    /**
     * Updated the goal displayed within the same row as the checkbox as completed in the db
     * @param view The checkbox clicked
     */
    public void updateCompleted(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        int rowId = (int) view.getTag();    // Tag is set in MonthlyGoalsAdapter.onBindViewHolder() to be the corresponding row position
        int goalId = monthlyGoalsAdapter.getMonthlyGoals().get(rowId).getMonthlyId();
        monthlyGoalsViewModel.updateCompleted(checked, goalId);
        // Display a toast with an animated check mark when a goal is completed.
        if (checked) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.completed_goal_toast, findViewById(R.id.completed_goal_toast));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
            ImageView checkMark = layout.findViewById(R.id.check_mark);
            ((Animatable) checkMark.getDrawable()).start();
        }
    }
}
