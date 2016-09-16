package com.example.harley.vanselow_habittracker;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Harley Vanselow on 2016-09-14.
 */
public class HabitDetailsModel {
    private Habit habit;

    public Habit getHabit() {
        return habit;
    }

    public HabitDetailsModel(String habitClicked) {
        readHabitProvided(habitClicked);
    }

    public void deleteHabitRecord(View view) {
        Date completionToDelete = (Date) ((View) view.getParent()).getTag();
        habit.getCompletionRecord().remove(completionToDelete);
        HabitIO.saveHabitToFile(habit, view.getContext());
    }

    private void readHabitProvided(String habitClicked) {
        Gson gson = new Gson();
        habit = gson.fromJson(habitClicked, Habit.class);
    }

    protected int getMissedCountValue() {
        Calendar creationDate = Calendar.getInstance();
        Calendar toCheck = Calendar.getInstance();
        Calendar completionDate = Calendar.getInstance();
        int missedCount = 0;
        creationDate.setTime(habit.getCreation());
        Date currentDate = new Date();
        for (Date checkDate = creationDate.getTime(); checkDate.before(currentDate); creationDate.add(Calendar.DATE, 1), checkDate = creationDate.getTime()) {
            toCheck.setTime(checkDate);
            boolean isCheckableDay = habit.getDays().contains(toCheck.get(Calendar.DAY_OF_WEEK));
            if (isCheckableDay) {
                boolean foundCompletion = false;
                for (Date completionNoted : habit.getCompletionRecord()) {
                    completionDate.setTime(completionNoted);
                    if (completionDate.get(Calendar.YEAR) == toCheck.get(Calendar.YEAR) &&
                            completionDate.get(Calendar.DAY_OF_YEAR) == toCheck.get(Calendar.DAY_OF_YEAR)) {
                        foundCompletion = true;
                        break;
                    }
                }
                if (!foundCompletion) {
                    missedCount++;
                }
            }
        }
        return missedCount;
    }

    public List<Date> getCompletionRecord() {
        return habit.getCompletionRecord();
    }
    public String getName(){
        return habit.getName();
    }
}
