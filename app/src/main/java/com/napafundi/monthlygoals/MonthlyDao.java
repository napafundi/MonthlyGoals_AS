package com.napafundi.monthlygoals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MonthlyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Monthly> goals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Monthly goal);

    @Update
    void update(Monthly goal);

    @Delete
    void delete(Monthly goal);

    @Query("SELECT * FROM Monthly")
    LiveData<List<Monthly>> findAll();
}
