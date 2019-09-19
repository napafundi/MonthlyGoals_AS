package com.napafundi.monthlygoals;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Calendar;
import java.util.Locale;

/**
 * Stores the data for each monthly goal
 * @author Nick Pafundi
 *
 */
@Entity
public class Monthly {
    @PrimaryKey(autoGenerate = true)
    private int monthly_id;

    @TypeConverters(CalendarTypeConverter.class)
    @ColumnInfo(name = "date")
    private Calendar date = Calendar.getInstance();

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "completed")
    private boolean completed;

    public Monthly() {}

    /**
     * Instantiates a Monthly object based on a given title, date and description. Completed is set to false by default. (You wouldn't create a goal you've already completed)
     * @param title The title for the goal
     * @param date The date for the goal
     * @param desc The description of the goal
     */
    public Monthly(String title, Calendar date, String desc) {
        this.title = title;
        this.date = date;
        this.description = desc;
        completed = false;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Used by the MonthlyTableModel to return the goal's month.
     * @return Returns a string representation of the goal's month. If the month's int value is outside the bounds, will display "error"
     */
    public String getMonth() {
        return date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getMonthly_id() {
        return monthly_id;
    }

    public void setMonthly_id(int monthly_id) {
        this.monthly_id = monthly_id;
    }
}
