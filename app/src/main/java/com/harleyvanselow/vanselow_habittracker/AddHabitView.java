package com.harleyvanselow.vanselow_habittracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class serves to populate a UI where a user can create a new habit. It reads the contents
 * of the onscreen text field and days of week checkboxes to create a new habit, then save it to
 * the data file using HabitIO. After the new habit is requested to be saved, this class will end the
 * activity, returning the user to the MainActivity view.
 *
 * One possible issue is that there is no validation of user input. It is considered valid for a user
 * to save a habit with no name and no days of the week selected, which will result in the habit never
 * appearing, but still existing in the save file.
 */
public class AddHabitView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
    }
    public void saveHabit(View view){
        TextView habitNameView = (TextView) findViewById(R.id.editText);
        List<Integer> checkedDays = getDaysOfWeek();
        String habitNameText = habitNameView.getText().toString();
        if(checkedDays.size()==0){
            Toast.makeText(this,"Please select at least one day",Toast.LENGTH_SHORT).show();
            return;
        }else if(habitNameText.length()==0){
            Toast.makeText(this,"Please name your habit",Toast.LENGTH_SHORT).show();
            return;
        }
        HabitIO.saveHabitToFile(new Habit(habitNameText,checkedDays),this);
        finish();
    }

    @NonNull
    private List<Integer> getDaysOfWeek() {
        List<Integer> checkedDays = new ArrayList<>();
        LinearLayout dayGroup= (LinearLayout) findViewById(R.id.dayGroup);
        getDays(checkedDays, dayGroup);
        return checkedDays;
    }
    private void getDays(List<Integer> checkedDays, LinearLayout dayGroup) {
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
    }



}
