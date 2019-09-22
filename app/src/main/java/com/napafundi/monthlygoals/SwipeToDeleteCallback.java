package com.napafundi.monthlygoals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private MonthlyGoalsViewModel monthlyGoalsViewModel;
    private MonthlyGoalsAdapter mAdapter;
    private Drawable trashIcon;
    private ColorDrawable background;

    public SwipeToDeleteCallback(Context context, MonthlyGoalsViewModel monthlyGoalsViewModel, MonthlyGoalsAdapter mAdapter) {
        super(0,ItemTouchHelper.LEFT);
        this.monthlyGoalsViewModel = monthlyGoalsViewModel;
        this.mAdapter = mAdapter;
        trashIcon = ContextCompat.getDrawable(context, R.drawable.baseline_delete_24);
        background = new ColorDrawable(Color.GRAY);
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Monthly goal = mAdapter.getMonthlyGoals().get(position);
        monthlyGoalsViewModel.deleteGoal(goal);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 10; // Ensures background is behind the rounded corners of itemView

        int iconMargin = (itemView.getHeight() - trashIcon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - trashIcon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + trashIcon.getIntrinsicHeight();

        if (dX < 0) { // Left swipe case
            if ((int)Math.abs(dX) > (itemView.getWidth() / 3)) {
                background = new ColorDrawable(Color.RED);
            }
            int iconLeft = itemView.getRight() - iconMargin - trashIcon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            trashIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else if (dX == 0) { // No swipe case
            background.setBounds(0,0,0,0);
        }
        background.draw(c);
        trashIcon.draw(c);
    }
}
