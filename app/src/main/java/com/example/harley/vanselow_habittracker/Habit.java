package com.example.harley.vanselow_habittracker;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by Harley on 9/9/2016.
 */
public class Habit implements Serializable {
    private String name;
    private DayOfWeek day;
    public Habit(String name,DayOfWeek day){
        this.name = name;
        this.day = day;
    }
}
