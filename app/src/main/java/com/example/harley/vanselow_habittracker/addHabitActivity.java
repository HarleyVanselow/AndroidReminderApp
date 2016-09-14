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

public class AddHabitActivity extends AppCompatActivity {

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
        for (int i = 0; i < dayGroup.getChildCount(); i++) {
            CheckBox day = (CheckBox) dayGroup.getChildAt(i);
            if(day.isChecked()){
                switch(day.getId()){
                    case R.id.mondayBox:
                        checkedDays.add(Calendar.MONDAY);
                        break;
                    case R.id.tuesdayBox:
                        checkedDays.add(Calendar.TUESDAY);
                        break;
                    case R.id.wednesdayBox:
                        checkedDays.add(Calendar.WEDNESDAY);
                        break;
                    case R.id.thursdayBox:
                        checkedDays.add(Calendar.THURSDAY);
                        break;
                    case R.id.fridayBox:
                        checkedDays.add(Calendar.FRIDAY);
                        break;
                    case R.id.saturdayBox:
                        checkedDays.add(Calendar.SATURDAY);
                        break;
                    case R.id.sundayBox:
                        checkedDays.add(Calendar.SUNDAY);
                        break;
                }
            }
        }
        return checkedDays;
    }

}
