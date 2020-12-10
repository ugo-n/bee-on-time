package com.example.checklist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.checklist.R;
import com.example.checklist.TaskItem;
import com.example.checklist.adapters.TaskAdapter;

import java.util.List;

public class Tasklist extends AppCompatActivity {
    //initialize items present in view
    EditText text; //edittext in which user will input task
    Button addButton; //button user will press to add task
    ListView listview; //list view in which the tasks will be displayed
    List<TaskItem> listItems; //arraylist which contains the data of created tasks
    TaskAdapter adapter; //adapter to bring tasks from arraylist into listview
    Intent intent; //intent to gain information from calling activity
    TextView listTitle; //title of the checklist
    Drawable bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        //add bg to activity and set text color of toolbar
        bg = getResources().getDrawable(R.drawable.listbg);
        getWindow().setBackgroundDrawable(bg);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFF769\">" + getString(R.string.app_name)+ "</font>"));

        //retrieve item from view, get intent from previous activity (checklist id)
        listTitle = (TextView) findViewById(R.id.textView);
        intent = getIntent();
        listTitle.setText(intent.getStringExtra("title")); //get title of list from previous activity
        final int id = intent.getIntExtra("id",0);
        text = (EditText) findViewById(R.id.task_input);
        addButton = (Button) findViewById(R.id.addButton);
        listview = (ListView) findViewById(R.id.list_view);

        //retrieve list of tasks with matching id to checklist, set adapter
        listItems = CreateList.taskDatabase.taskDao().getMyData(id);
        adapter = new TaskAdapter(this, listItems);
        listview.setAdapter(adapter);

        //add button for addding new tasks to listview/database
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!text.getText().toString().equals("")) {
                    TaskItem newTask = new TaskItem();
                    newTask.setChecked(false);
                    newTask.setTasktext(text.getText().toString());
                    newTask.setListID(id);
                    listItems.add(newTask);
                    CreateList.taskDatabase.taskDao().addData(newTask);
                    adapter.notifyDataSetChanged();
                    text.setText("");
                }//closes keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addButton.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });
    }
}