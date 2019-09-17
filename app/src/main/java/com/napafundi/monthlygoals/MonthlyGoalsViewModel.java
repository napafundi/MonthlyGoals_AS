package com.napafundi.monthlygoals;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonthlyGoalsViewModel extends AndroidViewModel {

    private MonthlyDao monthlyDao;
    private ExecutorService executorService;

    public MonthlyGoalsViewModel(@NonNull Application application) {
        super(application);
        monthlyDao = MonthlyGoalsDatabase.getInstance(application).monthlyDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Monthly>> getAllGoals() {
        return monthlyDao.findAll();
    }

    void saveGoal(Monthly goal) {
        executorService.execute(() -> monthlyDao.save(goal));
    }

    void deleteGoal(Monthly goal) {
        executorService.execute(() -> monthlyDao.delete(goal));
    }
}
