package com.example.harley.vanselow_habittracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddHabitView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
    }
    public void saveHabit(View view){
        List<Integer> checkedDays = getDayOfWeeks();
        TextView habitNameView = (TextView) findViewById(R.id.editText);
        String habitNameText = habitNameView.getText().toString();
        HabitIO.saveHabitToFile(new Habit(habitNameText,checkedDays),this);
        finish();
    }

    @NonNull
    private List<Integer> getDayOfWeeks() {
        List<Integer> checkedDays = new ArrayList<>();
        LinearLayout dayGroup= (LinearLayout) findViewById(R.id.dayGroup);
        AddHabitModel.getDays(checkedDays, dayGroup);
        return checkedDays;
    }



}
