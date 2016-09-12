package com.example.harley.vanselow_habittracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Harley Vanselow on 2016-09-10.
 */
public class HabitIO {
    final private static String DATA_STORE = "habitData.json";
    public static ArrayList<Habit> readHabitsFromFile(Context context){
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        Type collectionType = new TypeToken<ArrayList<Habit>>(){}.getType();
        ArrayList<Habit> habits = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(DATA_STORE);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
        habits = gson.fromJson(stringBuilder.toString(), collectionType);
        } catch (FileNotFoundException e) {
            return new ArrayList<Habit>();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return habits;
    }
    public static void saveHabitToFile(Habit habit,Context context){
        ArrayList<Habit> habits = readHabitsFromFile(context);
        addHabitToList(habit,habits,context);
        saveHabitsToFile(habits, context);
    }

    private static void saveHabitsToFile(ArrayList<Habit> habits, Context context) {
        Gson gson = new Gson();
        String out = gson.toJson(habits);
        FileOutputStream fileOutputStream;
        try{
            fileOutputStream = context.openFileOutput(DATA_STORE,Context.MODE_PRIVATE);
            fileOutputStream.write(out.getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void clearHabits(Context context){
        context.deleteFile(DATA_STORE);
    }

    public static void addHabitToList(Habit habit,ArrayList<Habit> habitsOnFile, Context context) {
        for(Habit onFile:habitsOnFile){
            if(onFile.getUniqueId().equals(habit.getUniqueId())){
                habitsOnFile.set(habitsOnFile.indexOf(onFile),habit);
                return;
            }
        }
        habitsOnFile.add(habit);
    }

    public static void deleteHabit(Habit habit,Context context) {
        ArrayList<Habit> habits = readHabitsFromFile(context);
        for(Habit existingHabit:habits){
            if(existingHabit.getUniqueId().equals(habit.getUniqueId())){
                habits.remove(existingHabit);
                saveHabitsToFile(habits, context);
                return;
            }
        }
        throw new NoSuchElementException("Habit not found on file, could not delete");
    }
}
