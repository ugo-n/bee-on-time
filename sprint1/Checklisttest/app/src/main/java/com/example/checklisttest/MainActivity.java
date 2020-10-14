package com.example.checklisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText text;
    ImageButton addButton;
    ListView listview;
    ArrayList<TaskItem> listItems;
    TaskItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.task_input);
        addButton = (ImageButton) findViewById(R.id.addButton);
        listview = (ListView) findViewById(R.id.list_view);
        listItems = new ArrayList<TaskItem>();
        listItems.add(new TaskItem(false, "This is an example task"));
        adapter = new TaskItemAdapter(this, listItems);
        listview.setAdapter(adapter);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                TaskItem newTask = new TaskItem(false,  text.getText().toString());
                listItems.add(newTask);
                adapter.notifyDataSetChanged();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
    ArrayList<TaskItem> arrayOfTasks = new ArrayList<TaskItem>();


    public void addTask(View view){
        EditText text = (EditText)findViewById(R.id.task_input);
        TaskItem newTask = new TaskItem(false,  text.getText().toString());

    }


}