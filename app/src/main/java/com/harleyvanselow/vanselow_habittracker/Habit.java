package com.harleyvanselow.vanselow_habittracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Harley on 9/9/2016.
 *
 */
public class Habit {
    private String name;
    private List<Integer> days;
    private List<Date> completionRecord;
    private final UUID uniqueId;
    private final Date creation;
    public Habit(String name, List<Integer> days) {
        this.name = name;
        this.days = days;
        this.creation = new Date();
        this.uniqueId = UUID.randomUUID();
        this.completionRecord = new ArrayList<>();
    }
    public Habit(String name,List<Integer> days, Date creation){
        this.name = name;
        this.days = days;
        this.creation = creation;
        this.uniqueId = UUID.randomUUID();
        this.completionRecord = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getDays() {
        return days;
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
