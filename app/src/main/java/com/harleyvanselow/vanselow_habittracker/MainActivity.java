package com.harleyvanselow.vanselow_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Harley Vanselow on 2016-09-10.
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
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        for (int i = 0; i < curHabits.size(); i++) {
            Habit habit = curHabits.get(i);
            if (!habit.getDays().contains(currentTime.get(Calendar.DAY_OF_WEEK))) {
                continue;
            }
            getLayoutInflater().inflate(R.layout.habit_layout, habitList);
            LinearLayout habitContainer = (LinearLayout) findViewById(R.id.newHabit);
            habitContainer.setId(i);
            habitContainer.setTag(habit);
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
