package com.napafundi.monthlygoals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MonthlyTest {
    private MonthlyDao monthlyDao;
    private MonthlyGoalsDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MonthlyGoalsDatabase.class).build();
        monthlyDao = db.getMonthlyDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void createGoalAndReadInList() throws Exception {
        String title = "test title";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        String desc = "test desc";
        Monthly goal = new Monthly(title, date, desc);
        monthlyDao.save(goal);
        List<Monthly> goals = monthlyDao.findAllList();
        Assert.assertThat(goals.get(0).getTitle(), Matchers.equalTo(goal.getTitle()));
    }

    @Test
    public void deleteGoal() throws Exception {
        String title = "test delete title";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        String desc = "test delete desc";
        Monthly goal = new Monthly(title, date, desc);
        monthlyDao.save(goal);
        List<Monthly> goals = monthlyDao.findAllList();
        Assert.assertThat(goals.get(0).getTitle(), Matchers.equalTo(goal.getTitle()));
        monthlyDao.delete(goals.get(0));
        List<Monthly> updatedGoals = monthlyDao.findAllList();
        Assert.assertTrue(updatedGoals.isEmpty());
    }

    @Test
    public void updateCompleted() throws Exception {
        String title = "test delete title";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        String desc = "test delete desc";
        Monthly goal = new Monthly(title, date, desc);
        monthlyDao.save(goal);
        List<Monthly> goals = monthlyDao.findAllList();
        Monthly test_goal = goals.get(0);
        Assert.assertFalse(test_goal.isCompleted());
        monthlyDao.updateCompleted(true, test_goal.getMonthlyId());
        goals = monthlyDao.findAllList();
        Assert.assertTrue(goals.get(0).isCompleted());
    }
}
