package com.napafundi.monthlygoals;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarTypeConverter {
    @TypeConverter
    public static Calendar calendarFromTimeStamp(String value) {
        if (value == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(value) * 1000);
        return cal;
    }

    @TypeConverter
    public static String dateToTimeStamp(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return "" + cal.getTimeInMillis()/1000;
    }
}
