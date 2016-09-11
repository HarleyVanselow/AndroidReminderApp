package com.example.harley.vanselow_habittracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);
    }
    public void saveHabit(View view){
        List<DayOfWeek> checkedDays = getDayOfWeeks();
        TextView habitNameView = (TextView) findViewById(R.id.editText);
        String habitNameText = habitNameView.getText().toString();
        HabitIO.saveHabitToFile(new Habit(habitNameText,checkedDays),this);
        finish();
    }



    @NonNull
    private List<DayOfWeek> getDayOfWeeks() {
        List<DayOfWeek> checkedDays = new ArrayList<>();
        LinearLayout dayGroup= (LinearLayout) findViewById(R.id.dayGroup);
        for (int i = 0; i < dayGroup.getChildCount(); i++) {
            CheckBox day = (CheckBox) dayGroup.getChildAt(i);
            if(day.isChecked()){
                switch(day.getId()){
                    case R.id.sundayBox:
                        checkedDays.add(DayOfWeek.Sunday);
                        break;
                    case R.id.mondayBox:
                        checkedDays.add(DayOfWeek.Monday);
                        break;
                    case R.id.tuesdayBox:
                        checkedDays.add(DayOfWeek.Tuesday);
                        break;
                    case R.id.wednesdayBox:
                        checkedDays.add(DayOfWeek.Wednesday);
                        break;
                    case R.id.thursdayBox:
                        checkedDays.add(DayOfWeek.Thursday);
                        break;
                    case R.id.fridayBox:
                        checkedDays.add(DayOfWeek.Friday);
                        break;
                    case R.id.saturdayBox:
                        checkedDays.add(DayOfWeek.Saturday);
                        break;
                }
            }
        }
        return checkedDays;
    }

    public void clearHabits(View view) {
        HabitIO.clearHabits(this);
    }
}
