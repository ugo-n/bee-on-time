package com.example.moodchart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //data file
    private static final String FILE_NAME = "coloredDays.txt";

    ImageView image;
    int x = 255;
    //for calendar use (days, times, etc.)
    Calendar calDate = Calendar.getInstance();
    int checkTime = calDate.get(Calendar.HOUR_OF_DAY);
    //for calling days on the grid
    int[]array = new int[]{R.id.day1,R.id.day2,R.id.day3,R.id.day4,R.id.day5,R.id.day6,R.id.day7,
                    R.id.day8,R.id.day9,R.id.day10,R.id.day11,R.id.day12,R.id.day13,R.id.day14,
                    R.id.day15,R.id.day16,R.id.day17,R.id.day18,R.id.day19,R.id.day20,R.id.day21,
                    R.id.day22,R.id.day23,R.id.day24,R.id.day25,R.id.day26, R.id.day27,R.id.day28,
                    R.id.day29,R.id.day30,R.id.day31,R.id.day32,R.id.day33,R.id.day34,R.id.day35,
                    R.id.day36,R.id.day37,R.id.day38,R.id.day39,R.id.day40,R.id.day41,R.id.day42};
    //for getting values of that month (how many days in total, etc. and formatting)
    int[]days = new int[]{1,2,3,4,5,6,7};
    int currentMonth = calDate.get(Calendar.MONTH);
    int fullDays = findDayAmt(currentMonth);
    SimpleDateFormat currentDayFormat = new SimpleDateFormat("d");
    String stringDay = currentDayFormat.format(calDate.getTime());
    int i = Integer.valueOf(stringDay)-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set action bar font color to yellow and the text to the app name
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFF769\">" + "beeOnTime"));

        //reset data if at end of the month at 11pm
        if (i == fullDays && checkTime == 22){
            resetData();
        }
        //create month
        createCurrentMonth();
        //cover skipped days
        skipDays();
        //load any old data
        load();
    }

    //skip days that are not used for that month
    public void skipDays(){
        //find the first day for this month in correct format
        SimpleDateFormat wordDayFormat = new SimpleDateFormat("EEEE");
        String getWordDay = wordDayFormat.format(calDate.getTime());
        int firstDay = getIntValOfDay(getWordDay);
        //TextView currentDay = findViewById(R.id.presentDay);
        //currentDay.setText(String.valueOf(firstDay));

        //set default value
        int daysExcluded = 0;

        //color first few days in the grid that are not part of the month
        for (int n=1; n < firstDay; n++){
            //find the day at the index - 1
            image = findViewById(array[n-1]);
            //set color to colored out day (means you can't put a mood for that day)
            image.setBackgroundColor(Color.parseColor("#532709"));
            //add to i (allows us to accurately color the current day)
            //if not added, the index will not shift correctly for the skipped days in the beginning
            i++;
            //count the days that you color out
            daysExcluded++;
        }

        //color the days after the last day of the month
        for (int n=fullDays+daysExcluded; n < 42; n++){
            //find the day at the index
            image = findViewById(array[n]);
            //set color to colored out day (means you can't put a mood for that day)
            image.setBackgroundColor(Color.parseColor("#532709"));
        }
    }

    //buttons for mood
    //when good button is clicked
    public void onGoodClick(View view){
        //find the current day
        image = findViewById(array[i]);
        //set the day to the color for good
        image.setBackgroundColor(Color.parseColor("#B4F6A4"));
        //save data
        save(i, "#B4F6A4");
    }
    //when okay button is clicked
    public void onOkayClick(View view){
        //find the current day
        image = findViewById(array[i]);
        //set the day to the color for okay
        image.setBackgroundColor(Color.parseColor("#F7FAA1"));
        //save data
        save(i, "#F7FAA1");
    }
    //when bad button is clicked
    public void onBadClick(View view){
        //find the current day
        image = findViewById(array[i]);
        //set the day to the color for bad
        image.setBackgroundColor(Color.parseColor("#F898A4"));
        //save data
        save(i, "#F898A4");
    }

    //print out current month, day, and year
    public void createCurrentMonth(){
        //print out current day
        //find id for day
        TextView currentDay = findViewById(R.id.presentDay);
        //retrieve day from SimpleDateFormat
        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        String getDay = dayFormat.format(calDate.getTime());
        //set id value to current day
        currentDay.setText(getDay);

        //print out current month
        //find id for month
        TextView currentMonth = findViewById(R.id.month);
        //array for string version of month
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        //retrieve string version of month by using the index given by .MONTH
        String month=monthName[calDate.get(Calendar.MONTH)];
        //set id value to current month
        currentMonth.setText(month);

        //print out current year
        //find id for year
        TextView currentYear = findViewById(R.id.year);
        //retrieve year from SimpleDateFormat
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String getYear = yearFormat.format(calDate.getTime());
        //set id value to current year
        currentYear.setText(getYear);

    }

    //find out what day to start the month
    public int getIntValOfDay(String day){
        //retrieve current day in needed format
        SimpleDateFormat numDayFormat = new SimpleDateFormat("d");
        String getNumDay = numDayFormat.format(calDate.getTime());
        int firstDayMonth = Integer.valueOf(getNumDay);
        int dayValue = 0;
        //set value according to what day is returned
        switch(day){
            case "Sunday":
                dayValue = 1;
                break;
            case "Monday":
                dayValue = 2;
                break;
            case "Tuesday":
                dayValue = 3;
                break;
            case "Wednesday":
                dayValue = 4;
                break;
            case "Thursday":
                dayValue = 5;
                break;
            case "Friday":
                dayValue = 6;
                break;
            case "Saturday":
                dayValue = 7;
                break;
        }
        //run through loop to subtract from the current day until the first day
        //this will give an accurate placement of the first day
        for (int i=1;i<firstDayMonth;i++){
            dayValue--;
            if (dayValue == 0){
                dayValue = 7;
            }
        }
        //return placement of the first day
        return dayValue;
    }

    //find total days in the current month
    public int findDayAmt(int month){
        //default value
        int dayAmt = 0;
        //switch case for figuring out amount of days
        switch (month){
            //if month is
            //January, March, May, July, August, October, or December
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                //set days to 31
                dayAmt = 31;
                break;
            //if the month is February
            case 1:
                //check for leap year
                if (checkLeapYear() == true) {
                    //set to 29 days if leap year
                    dayAmt = 29;
                }
                //otherwise
                else {
                    //set to 28 days for regular years
                    dayAmt = 28;
                }
                break;
            //if the month is
            //April, June, September, or November
            case 3:
            case 5:
            case 8:
            case 10:
                //set days to 30
                dayAmt = 30;
                break;
        }
        return dayAmt;
    }

    public boolean checkLeapYear(){
        SimpleDateFormat cYearFormat = new SimpleDateFormat("yyyy");
        String getCYear = cYearFormat.format(calDate.getTime());
        int cYear = Integer.parseInt(getCYear);
        boolean leapYear = false;
        //check if year is divisible by 4
        if (cYear % 4 == 0){
            //check if year is divisible by 100
            if (cYear % 100 == 0){
                //check if year is divisible by 400
                if (cYear % 400 == 0){
                    //if all of the above are true, it's a leap year
                    leapYear = true;
                }
                //otherwise, it is not a leap year
                else {
                    leapYear = false;
                }
            }
            //otherwise if year is divisible by 4 but not by 100
            else {
                //it is a leap year
                leapYear = true;
            }
        }
        //if not divisible by 4, it is not a leap year
        else {
            leapYear = false;
        }
        return leapYear;
    }

    //save current day and mood from user's choice
    public void save(int dayNum, String mood){
        //putting the date id and string together
        //separate with comma for easy separation during load
        String text = Integer.toString(dayNum) + "," + mood;

        FileOutputStream fileOutput = null;

        try {
            //open file for appending
            fileOutput = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            //write data info
            fileOutput.write(text.getBytes());
            //break into new line
            fileOutput.write(10);
            //tell user it was saved
            Toast.makeText(this, "Saved!",Toast.LENGTH_SHORT).show();
        }
        //error
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutput != null){
                try {
                    //close file
                    fileOutput.close();
                }
                //error
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //load saved days and moods
    public void load(){
        FileInputStream fileInput = null;
        //list - to be used for all colored days
        List<String> allColors = new ArrayList<String>();
        try {
            //open file
            fileInput = openFileInput(FILE_NAME);
            InputStreamReader isReader = new InputStreamReader(fileInput);
            BufferedReader bReader = new BufferedReader(isReader);
            String text;

            //run through all lines in text file
            while (((text = bReader.readLine()) != null)){
                //add each line to their own index in list
                allColors.add(text);
            }
        }
        //error
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (fileInput != null){
                try {
                    //close file
                    fileInput.close();
                }
                //error
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //run through all found days in text file
        for (int x=0; x<allColors.size(); x++){
            //temp variable holder
            String tempDay = allColors.get(x);

            //split day information into the day index and the mood
            List<String> infoArr = new ArrayList<>(Arrays.asList(tempDay.split(",")));

            //color day at index based on mood
            image = findViewById(array[Integer.parseInt(infoArr.get(0))]);
            image.setBackgroundColor(Color.parseColor(infoArr.get(1)));
        }

    }

    //reset data for new month
    public void resetData(){
        //delete file
        File dFile = new File("coloredDays.txt");
        dFile.delete();
    }
}