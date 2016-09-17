package com.harleyvanselow.vanselow_habittracker;

import android.app.Instrumentation;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by Harley Vanselow on 2016-09-10.
 */
public class HabitIOTest {

    @Test
    public void testReadHabitsFromFile() throws Exception {

    }

    @Test
    public void testSaveHabitToFile() throws Exception {
        Habit testHabit = new Habit("Test Habit", Arrays.asList(Calendar.MONDAY,Calendar.THURSDAY));

    }

    @Test
    public void testAddHabitToList() throws Exception {

    }

    @Test
    public void testDeleteHabit() throws Exception {

    }
}