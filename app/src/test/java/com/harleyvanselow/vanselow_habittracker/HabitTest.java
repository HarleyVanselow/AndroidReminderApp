package com.harleyvanselow.vanselow_habittracker;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Harley Vanselow on 2016-10-02.
 */

public class HabitTest {
    List<Integer> sampleDays;
    @Before
    public void setUp(){
        sampleDays = new ArrayList<>();
        sampleDays.add(1);
    }
    @Test
    public void testUniqueness(){
        Set<Habit> habitSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            //the .add method of set will return false if it already contained an identical element
            Assert.assertTrue(habitSet.add(new Habit("Same name, different contents", sampleDays)));
        }
    }
    @Test
    public void testGetName(){
        Habit testHabit = new Habit("Hi",sampleDays);
        Assert.assertEquals("Hi",testHabit.getName());
    }
    @Test
    public void testGetDays(){
        List<Integer> testEmptyDays = new ArrayList<>();
        Habit testHabit = new Habit("Hi",sampleDays);
        Habit testHabit1 = new Habit("Hi",testEmptyDays);
        Assert.assertTrue(testHabit.getDays().contains(1));
        Assert.assertEquals(0,testHabit1.getDays().size());
    }
    @Test
    public void testAddCompletionRecord(){
        Date currentDate = new Date();
        Habit testHabit = new Habit("Hi",sampleDays,currentDate);
        testHabit.addCompletionRecord();
        Assert.assertTrue(testHabit.getCompletionRecord().contains(currentDate));
        Date randomDate = new Date(1991,1,1);
        Assert.assertFalse(testHabit.getCompletionRecord().contains(randomDate));
    }
}
