package com.napafundi.monthlygoals;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MonthlyGoalsAdapter extends RecyclerView.Adapter<MonthlyGoalsAdapter.MonthlyGoalsHolder> {
    private List<Monthly> monthlyGoals = new ArrayList<>();
    private int globalPosition = -1;
    private Context context;

    MonthlyGoalsAdapter(MainActivity mainActivity, MainActivity mainActivity1) {
    }

    class MonthlyGoalsHolder extends RecyclerView.ViewHolder {
        LinearLayout monthlyGoalRow;
        TextView titleTextView;
        TextView dateTextView;
        CheckBox completedCheckBox;

        MonthlyGoalsHolder(@NonNull View itemView) {
            super(itemView);

            monthlyGoalRow = itemView.findViewById(R.id.monthly_goal_row);
            titleTextView = itemView.findViewById(R.id.goal_title);
            dateTextView = itemView.findViewById(R.id.goal_month);
            completedCheckBox = itemView.findViewById(R.id.goal_completed);
        }
    }



    @NonNull
    @Override
    public MonthlyGoalsAdapter.MonthlyGoalsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        View monthlyGoalsView = inflater.inflate(R.layout.monthly_goal_row, parent, false);

        return new MonthlyGoalsHolder(monthlyGoalsView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyGoalsAdapter.MonthlyGoalsHolder holder, int position) {
        Monthly goal = monthlyGoals.get(position);

        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(goal.getTitle());

        TextView dateTextView = holder.dateTextView;
        dateTextView.setText(goal.getMonth());

        CheckBox completedCheckBox = holder.completedCheckBox;
        completedCheckBox.setChecked(goal.isCompleted());
        holder.completedCheckBox.setTag(position);

        // Make rows selectable
        holder.monthlyGoalRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalPosition = position;
                notifyDataSetChanged();
            }
        });
        // Change background color of selected row, revert colors of rows that are no longer selected
        if (globalPosition == position) {
            holder.monthlyGoalRow.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.monthlyGoalRow.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return monthlyGoals.size();
    }

    void setData(List<Monthly> newData) {
        this.monthlyGoals = newData;
        notifyDataSetChanged();
    }

    List<Monthly> getMonthlyGoals() {
        return monthlyGoals;
    }

    Context getContext() {
        return context;
    }
}
