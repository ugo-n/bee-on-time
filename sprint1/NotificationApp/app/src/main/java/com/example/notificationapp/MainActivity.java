package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // set notification id and message
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("Reminder", editText.getText().toString());


        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) { //setting the clock to the current time
            case R.id.setBtn:
            // set alarm
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
            // cancel alarm
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
        }
        editText.setText(" ");

    }
}