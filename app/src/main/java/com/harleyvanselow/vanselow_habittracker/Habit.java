package com.harleyvanselow.vanselow_habittracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Harley on 9/9/2016.
 *
 * This class serves as the object representation of the "habits" that users can create. The class
 * includes information about the habit name, creation date, and completion record. Additionally, each
 * habit has a unique ID, using a random UUID. This ensures every habit created can be correctly tracked,
 * and it can be ensured multiple copies of the same habit are not stored in the file.
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
