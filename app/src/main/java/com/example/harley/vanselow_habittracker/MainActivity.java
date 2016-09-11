package com.example.harley.vanselow_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Harley Vanselow on 2016-09-10.
 */
public class MainActivity extends AppCompatActivity {
    final public static String HABIT = "com.example.harley.vanselow_habittracker.HABIT";
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
        for (int i = 0; i < curHabits.size(); i++) {
            inflateNewHabitLayout(habitList,i,curHabits);
        }
    }

    private void inflateNewHabitLayout(LinearLayout habitList, int index, List<Habit> curHabits) {
        Habit habit = curHabits.get(index);
        getLayoutInflater().inflate(R.layout.habit_layout,habitList);
        LinearLayout habitContainer = (LinearLayout) findViewById(R.id.newHabit);
        habitContainer.setId(index);
        habitContainer.setTag(habit);
        TextView habitView = (TextView)habitContainer.getChildAt(1);
        String text = habit.getName() + ": Completed " + habit.getCompletionRecord().size() + " times";
        habitView.setText(text);
    }

    public void launchHabitCreator(View view) {
        Intent intent = new Intent(this, AddHabitActivity.class);
        startActivity(intent);
    }

    public void completeHabit(View view) {
        Habit habit = (Habit)((View)view.getParent()).getTag();
        habit.addCompletionRecord();
        HabitIO.saveHabitToFile(habit,this);
        updateDisplay();
    }

    public void launchHabitDetails(View view) {
        Gson gson = new Gson();
        Habit clickedHabit = (Habit)((View)view.getParent()).getTag();
        String serializedHabit = gson.toJson(clickedHabit);
        Intent intent = new Intent(this,HabitDetails.class);
        intent.putExtra(HABIT,serializedHabit);
        startActivity(intent);
    }
}
