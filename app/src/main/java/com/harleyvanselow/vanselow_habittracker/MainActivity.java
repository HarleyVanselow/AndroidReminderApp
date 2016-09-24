package com.harleyvanselow.vanselow_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Harley Vanselow on 2016-09-10.
 *
 * This class represents the main page of the app, where users can view their habits for the day,
 * mark completions, and create new habits. The class has methods to launch other pages in the app,
 * and inflate views within itself to display habits. Each habit view listed within the main activity
 * is tagged with its relevant habit, which is serialized and passed on as a String extra into the
 * Habit Details view when a habit is pressed. This design intuitively maintains the idea of each
 * entry in the list being a "habit", as the view itself has all the relevant data.
 */
public class MainActivity extends AppCompatActivity {
    final public static String HABIT = "com.harleyvanselow.vanselow_habittracker.HABIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDisplay();
    }

    private void updateDisplay() {
        List<Habit> curHabits = HabitIO.readHabitsFromFile(this);
        LinearLayout habitList = (LinearLayout) findViewById(R.id.habitList);
        habitList.removeAllViews();
        inflateHabitList(curHabits, habitList);
    }

    private void inflateHabitList(List<Habit> curHabits, LinearLayout habitList) {
        final Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        ArrayMap<Integer,Habit> pendingInflation = new ArrayMap<>();
        for (int i = 0; i < curHabits.size(); i++) {
            Habit habit = curHabits.get(i);
            if (habit.getDays().contains(currentTime.get(Calendar.DAY_OF_WEEK))) {
                inflateHabit(habitList,i,habit,true);
            }
            else{
                pendingInflation.put(i,habit);
            }
        }
        for(Map.Entry<Integer,Habit> habitEntry:pendingInflation.entrySet()){
            inflateHabit(habitList,habitEntry.getKey(),habitEntry.getValue(),false);
        }
    }

    private void inflateHabit(LinearLayout habitList, int i, Habit habit, boolean isToday) {
        getLayoutInflater().inflate(R.layout.habit_layout, habitList);
        LinearLayout habitContainer = (LinearLayout) findViewById(R.id.newHabit);
        habitContainer.setId(i);
        habitContainer.setTag(habit);
        if(isToday){
            habitContainer.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        TextView habitName = (TextView) habitContainer.getChildAt(1);
        TextView habitCount = (TextView) habitContainer.getChildAt(0);
        int dailyCompletions = getDailyCompletions(habit);
        habitCount.setText(String.valueOf(dailyCompletions));
        if (dailyCompletions > 0) {
            habitCount.setBackgroundColor(getResources().getColor(R.color.createButtonColor));
        } else {
            habitCount.setBackgroundColor(getResources().getColor(R.color.colorWarning));
        }
        habitName.setText(habit.getName());
    }

    private int getDailyCompletions(Habit habit) {
        int dailyCompletions = 0;
        for (Date completions : habit.getCompletionRecord()) {
            if (DateUtils.isToday(completions.getTime())) {
                dailyCompletions++;
            }
        }
        return dailyCompletions;
    }

    public void launchHabitCreator(View view) {
        Intent intent = new Intent(this, AddHabitView.class);
        startActivity(intent);
    }

    public void completeHabit(View view) {
        Habit habit = (Habit) ((View) view.getParent()).getTag();
        habit.addCompletionRecord();
        HabitIO.saveHabitToFile(habit, this);
        updateDisplay();
    }

    public void launchHabitDetails(View view) {
        Gson gson = new Gson();
        Habit clickedHabit = (Habit) ((View) view.getParent()).getTag();
        String serializedHabit = gson.toJson(clickedHabit);
        Intent intent = new Intent(this, HabitDetailsView.class);
        intent.putExtra(HABIT, serializedHabit);
        startActivity(intent);
    }
}
