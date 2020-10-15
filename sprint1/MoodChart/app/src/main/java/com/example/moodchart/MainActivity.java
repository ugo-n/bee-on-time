package com.example.moodchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    int x = 255;
    int i = 0;
    Calendar calDate = Calendar.getInstance();
    int[]array = new int[]{R.id.day1,R.id.day2,R.id.day3,R.id.day4,R.id.day5,R.id.day6,R.id.day7,
                    R.id.day8,R.id.day9,R.id.day10,R.id.day11,R.id.day12,R.id.day13,R.id.day14,
                    R.id.day15,R.id.day16,R.id.day17,R.id.day18,R.id.day19,R.id.day20,R.id.day21,
                    R.id.day22,R.id.day23,R.id.day24,R.id.day25,R.id.day26, R.id.day27,R.id.day28,
                    R.id.day29,R.id.day30,R.id.day31,R.id.day32,R.id.day33,R.id.day34,R.id.day35};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCurrentMonth();
        skipDays();
    }

    public void skipDays(){
        int minDays = calDate.getFirstDayOfWeek();
        for (int n=0; n <= minDays; n++){
            image = findViewById(array[n]);
            image.setBackgroundColor(Color.parseColor("#9BE0F1"));
            i++;
        }

        for (int x=32;x<35;x++){
            image = findViewById(array[x]);
            image.setBackgroundColor(Color.parseColor("#9BE0F1"));
        }
    }

    public void onGoodClick(View view){
        if (i<=31){
            image = findViewById(array[i]);
            image.setBackgroundColor(Color.parseColor("#B4F6A4"));
            i++;
        }
    }
    public void onOkayClick(View view){
        if (i<=31) {
            image = findViewById(array[i]);
            image.setBackgroundColor(Color.parseColor("#F7FAA1"));
            i++;
        }
    }
    public void onBadClick(View view){
        if (i<=31){
            image = findViewById(array[i]);
            image.setBackgroundColor(Color.parseColor("#F898A4"));
            i++;
        }
    }

    public void createCurrentMonth(){
        TextView currentDay = findViewById(R.id.presentDay);
        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        String getDay = dayFormat.format(calDate.getTime());
        currentDay.setText(getDay);

        TextView currentMonth = findViewById(R.id.month);
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month=monthName[calDate.get(Calendar.MONTH)];
        currentMonth.setText(month);

        TextView currentYear = findViewById(R.id.year);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String getYear = yearFormat.format(calDate.getTime());
        currentYear.setText(getYear);

    }


}