package com.napafundi.monthlygoals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Stores the data for each monthly goal
 * @author Nick Pafundi
 *
 */
public class Monthly {
    private int monthly_id;
    private Calendar date = Calendar.getInstance();
    private String title;
    private String description;
    private boolean completed;

    /**
     * @param rs The MySQL ResultSet containing the data to instantiate a Monthly object
     */
    public Monthly(ResultSet rs) {
        try {
            monthly_id = rs.getInt("monthly_id");
            date.setTime(rs.getDate("date"));
            title = rs.getString("title");
            description = rs.getString("description");
            completed = rs.getBoolean("completed");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Instantiates a Monthly object based on a given title, date and description. Completed is set to false by default. (You wouldn't create a goal you've already completed)
     * @param title The title for the goal
     * @param date The date for the goal
     * @param desc The description of the goal
     */
    public Monthly(String title, Date date, String desc) {
        this.title = title;
        this.date.setTime(date);
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
        int monthNum = Calendar.MONTH;
        String month = "error";
        if (monthNum >= 0 && monthNum <= 11) {
            month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        }
        return month;
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

    public int getID() {
        return monthly_id;
    }
}
