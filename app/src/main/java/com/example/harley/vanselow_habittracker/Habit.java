package com.example.harley.vanselow_habittracker;

import java.util.Date;
import java.util.List;

/**
 * Created by Harley on 9/9/2016.
 */
public class Habit {
    private String name;
    private List<DayOfWeek> days;
    private List<Date> completionRecord;

    public Habit(String name, List<DayOfWeek> days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
    }
}
