package com.napafundi.monthlygoals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonthlyGoalsAdapter extends RecyclerView.Adapter<MonthlyGoalsAdapter.MonthlyGoalsHolder> {

    public MonthlyGoalsAdapter(MainActivity mainActivity, MainActivity mainActivity1) {
    }

    public class MonthlyGoalsHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView dateTextView;
        public CheckBox completedCheckBox;

        public MonthlyGoalsHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.goal_title);
            dateTextView = itemView.findViewById(R.id.goal_month);
            completedCheckBox = itemView.findViewById(R.id.goal_completed);
        }
    }

    private List<Monthly> monthlyGoals = new ArrayList<>();

    @NonNull
    @Override
    public MonthlyGoalsAdapter.MonthlyGoalsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
    }

    @Override
    public int getItemCount() {
        return monthlyGoals.size();
    }

    public void setData(List<Monthly> newData) {
        this.monthlyGoals = newData;
        notifyDataSetChanged();
    }
}
