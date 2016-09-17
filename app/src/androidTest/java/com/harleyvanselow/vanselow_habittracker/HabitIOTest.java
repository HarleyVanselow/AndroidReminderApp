package com.harleyvanselow.vanselow_habittracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Harley Vanselow on 2016-09-10.
 */
@RunWith(AndroidJUnit4.class)
public class HabitIOTest {
    Context context;
    Habit testHabit;
    @Before
    public void setContext(){
        context = InstrumentationRegistry.getTargetContext();
        testHabit = new Habit("Test Habit", Arrays.asList(Calendar.MONDAY,Calendar.THURSDAY));
    }

    @After
    public void cleanData(){
        HabitIO.clearHabits(context);
    }
    @Test
    public void testClean(){
        HabitIO.saveHabitToFile(testHabit,context);
        HabitIO.clearHabits(context);
        List<Habit> habits= HabitIO.readHabitsFromFile(context);
        Assert.assertEquals(0,habits.size());
    }

    @Test
    public void testReadWrite() throws Exception {
        HabitIO.saveHabitToFile(testHabit, context);
        ArrayList<Habit> habits = HabitIO.readHabitsFromFile(context);
        Assert.assertEquals(1,habits.size());
        Assert.assertEquals("Test Habit",habits.get(0).getName());
        Assert.assertEquals(2,habits.get(0).getDays().size());
    }

    @Test
    public void testAddHabitToList() throws Exception {
        ArrayList<Habit> habits = new ArrayList<>();
        Habit editedHabit = getEditedHabit(testHabit);
        HabitIO.addHabitToList(editedHabit,habits);
        Assert.assertEquals(1,habits.size());
        Assert.assertEquals("New Test Habit",habits.get(0).getName());
        HabitIO.addHabitToList(new Habit("Other Habit",Arrays.asList(1)),habits);
        Assert.assertEquals(2,habits.size());

    }

    private Habit getEditedHabit(Habit habitToEdit) {
        Gson gson= new Gson();
        String s = gson.toJson(habitToEdit);
        s=s.replace("Test Habit","New Test Habit");
        return gson.fromJson(s,Habit.class);
    }

    @Test
    public void testDeleteHabit() throws Exception {
        HabitIO.saveHabitToFile(testHabit,context);
        HabitIO.saveHabitToFile(new Habit("new test",Arrays.asList(1)),context);
        HabitIO.deleteHabit(testHabit,context);
        ArrayList<Habit> habits = HabitIO.readHabitsFromFile(context);
        Assert.assertEquals(1, habits.size());
        Assert.assertEquals("new test",habits.get(0).getName());
    }
}