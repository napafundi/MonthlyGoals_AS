package com.napafundi.monthlygoals;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class MonthlyGoalsAdapter extends RecyclerView.Adapter<MonthlyGoalsAdapter.MonthlyGoalsHolder> {
    private List<Monthly> monthlyGoals = new ArrayList<>();
    private int globalPosition = -1;
    private MainActivity mainActivity;
    private Snackbar goalSnackbar;

    MonthlyGoalsAdapter(MainActivity mainActivity, MainActivity mainActivity1) {
        this.mainActivity = mainActivity;
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
            Drawable background = holder.monthlyGoalRow.getBackground();
            if (background instanceof ColorDrawable) {
                int color = ((ColorDrawable) background).getColor();
                if (color == Color.LTGRAY) { // Revert background to transparent if selecting the already selected row, hide goal description
                    holder.monthlyGoalRow.setBackgroundColor(Color.TRANSPARENT);
                    goalSnackbar.dismiss();
                } else { // Make background light gray to show it is selected, display goal's description within a snackbar
                    holder.monthlyGoalRow.setBackgroundColor(Color.LTGRAY);
                    String desc = goal.getDescription();
                    showGoalDescriptionSnackBar(desc);
                }
            }
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

    private void showGoalDescriptionSnackBar(String desc) {
        View view = mainActivity.findViewById(R.id.main_activity);
        goalSnackbar = Snackbar.make(view, desc, Snackbar.LENGTH_INDEFINITE);
        goalSnackbar.show();
    }
}
