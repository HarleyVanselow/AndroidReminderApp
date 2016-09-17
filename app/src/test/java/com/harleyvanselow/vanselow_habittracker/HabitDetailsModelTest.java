package com.harleyvanselow.vanselow_habittracker;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harley Vanselow on 2016-09-17.
 */
public class HabitDetailsModelTest {
    private HabitDetailsModel habitDetailsModel;

    @Test
    public void testGetHabit() throws Exception {
        Gson gson = new Gson();
        Habit testHabit = new Habit("Test Habit", Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.FRIDAY, Calendar.SUNDAY));
        habitDetailsModel = new HabitDetailsModel(gson.toJson(testHabit));
        Habit parsedHabit = habitDetailsModel.getHabit();

        Assert.assertEquals(testHabit.getName(), parsedHabit.getName());
        Assert.assertEquals(testHabit.getDays(), parsedHabit.getDays());
        Assert.assertEquals(testHabit.getUniqueId(), parsedHabit.getUniqueId());
    }

    @Test
    public void testGetMissedCountValue() throws Exception {
        int timeDisplacement = -7;
        List<Integer> days = Arrays.asList(Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.FRIDAY, Calendar.SUNDAY);
        setTestHabitDetailsModel(days, timeDisplacement);
        Assert.assertEquals(days.size(),habitDetailsModel.getMissedCountValue());

        days = Arrays.asList(Calendar.MONDAY, Calendar.TUESDAY,Calendar.WEDNESDAY, Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY,Calendar.SUNDAY);
        setTestHabitDetailsModel(days,timeDisplacement);
        Assert.assertEquals(days.size()+1,habitDetailsModel.getMissedCountValue());
        habitDetailsModel.getHabit().addCompletionRecord();
        Assert.assertEquals(days.size(),habitDetailsModel.getMissedCountValue());
    }

    private void setTestHabitDetailsModel(List<Integer> days, int timeDisplacement) {
        Gson gson = new Gson();
        Date creationDate = new Date(System.currentTimeMillis() + timeDisplacement*1000*60*60*24);
        Habit testHabit = new Habit("Test Habit", days,creationDate);
        habitDetailsModel = new HabitDetailsModel(gson.toJson(testHabit));
    }
}