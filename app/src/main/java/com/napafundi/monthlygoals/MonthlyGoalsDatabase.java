package com.napafundi.monthlygoals;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Monthly.class}, version=1)
public abstract class MonthlyGoalsDatabase extends RoomDatabase {

    private static MonthlyGoalsDatabase INSTANCE;

    public abstract MonthlyDao monthlyDao();

    private static final Object sLock = new Object();

    public static MonthlyGoalsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MonthlyGoalsDatabase.class, "MonthlyGoals.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

    public MonthlyDao getMonthlyDao() {
        return monthlyDao();
    }
}
