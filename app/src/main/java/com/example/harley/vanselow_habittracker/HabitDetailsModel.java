package com.example.harley.vanselow_habittracker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Harley Vanselow on 2016-09-14.
 */
public class HabitDetailsModel {
    protected static int getMissedCountValue(Habit habit) {
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

}
