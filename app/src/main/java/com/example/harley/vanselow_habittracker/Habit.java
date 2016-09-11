package com.example.harley.vanselow_habittracker;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Harley on 9/9/2016.
 */
public class Habit {
    private String name;
    private List<DayOfWeek> days;
    private List<Date> completionRecord;
    private final UUID uniqueId;
    private final Date creation;
    public Habit(String name, List<DayOfWeek> days) {
        this.name = name;
        this.days = days;
        this.creation = new Date();
        this.uniqueId = UUID.randomUUID();
        this.completionRecord = new ArrayList<>();
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

    public Date getCreation() {
        return creation;
    }

    public List<Date> getCompletionRecord() {
        return completionRecord;
    }

    public void addCompletionRecord() {
        this.completionRecord.add(new Date());
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
