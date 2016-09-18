package com.harleyvanselow.vanselow_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
/**
* This class serves to display details about a particular habit's completion record. This class will
 * have access to a serialized Habit from MainActivity, which it passes off to HabitDetailModel to
 * deserialize and process, in compliance with MVC architecture. It has the ability to inflate a
 * scrolling list of completion records for the given Habit, as well as present data about completion
 * stats. Each completion record listed also has a deletion button, which removes the completion record
 * and updates the rest of the view.
* */
public class HabitDetailsView extends AppCompatActivity {
    private HabitDetailsModel habitDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);
        String habitString = getIntent().getStringExtra(MainActivity.HABIT);
        habitDetailsModel = new HabitDetailsModel(habitString);
        setTitle(habitDetailsModel.getName());
        setCompletionCount();
        setMissedCount();
        loadCompletionRecords();
    }

    private void setMissedCount() {
        int missedCount = habitDetailsModel.getMissedCountValue();
        TextView missedCountView = (TextView) findViewById(R.id.missedCount);
        missedCountView.setText(String.valueOf(missedCount));
    }

    private void setCompletionCount() {
        TextView completionCountView = (TextView) findViewById(R.id.completionCount);
        completionCountView.setText(String.valueOf(habitDetailsModel.getCompletionRecord().size()));
    }



    private void loadCompletionRecords() {
        LinearLayout habitCompletionList = (LinearLayout) findViewById(R.id.completionList);
        habitCompletionList.removeAllViews();
        List<Date> completionRecordList = habitDetailsModel.getCompletionRecord();
        for (Date completion : completionRecordList) {
            getLayoutInflater().inflate(R.layout.habit_completion_record, habitCompletionList);
            RelativeLayout completionRecordContainer = (RelativeLayout) findViewById(R.id.newCompletionRecord);
            completionRecordContainer.setId(completionRecordList.indexOf(completion));
            completionRecordContainer.setTag(completion);
            TextView completionRecord = (TextView) completionRecordContainer.getChildAt(0);
            completionRecord.setText(formatDate(completion));
        }
    }

    private String formatDate(Date completion) {
        SimpleDateFormat format = new SimpleDateFormat("MMM d, HH:mm");
        return format.format(completion);
    }

    public void deleteRecord(View view) {
        habitDetailsModel.deleteHabitRecord(view);
        loadCompletionRecords();
        setMissedCount();
        setCompletionCount();
    }
    public void deleteHabit(View view) {
        try {
            HabitIO.deleteHabit(habitDetailsModel.getHabit(), view.getContext());
        } catch (NoSuchElementException e) {
            Toast.makeText(HabitDetailsView.this, "Habit not found!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
