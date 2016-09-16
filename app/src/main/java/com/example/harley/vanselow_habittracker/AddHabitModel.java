package com.example.harley.vanselow_habittracker;

import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Harley on 9/15/2016.
 */
public class AddHabitModel {
    public static void getDays(List<Integer> checkedDays, LinearLayout dayGroup) {
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
