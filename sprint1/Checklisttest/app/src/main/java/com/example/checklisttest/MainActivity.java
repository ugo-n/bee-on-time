package com.example.checklisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //initialize items present in view
    EditText text; //edittext in which user will input task
    ImageButton addButton; //button user will press to add task
    ListView listview; //list view in which the tasks will be displayed
    ArrayList<TaskItem> listItems; //arraylist which contains the data of created tasks
    TaskItemAdapter adapter; //adapter to bring tasks from arraylist into listview
    Intent intent; //intent to gain information from calling activity
    TextView listTitle; //title of the checklist
    Button backButton; //back button (will be implemented later)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTitle = (TextView) findViewById(R.id.textView);
        intent = getIntent();
        listTitle.setText(intent.getStringExtra("title")); //get title of list from previous activity
        text = (EditText) findViewById(R.id.task_input);
        addButton = (ImageButton) findViewById(R.id.addButton);
        listview = (ListView) findViewById(R.id.list_view);
        backButton = (Button) findViewById(R.id.backButton);




        listItems = new ArrayList<TaskItem>();
        if (savedInstanceState != null) { //if bundle is present
            ArrayList<TaskItem> savedItems = new ArrayList<TaskItem>();
            for(int i = 0; i < savedInstanceState.getStringArrayList("taskStatus").size(); i++){
                if(savedInstanceState.getStringArrayList("taskStatus").get(i).equals("T")){
                    savedItems.set(0, new TaskItem(true,
                            savedInstanceState.getStringArrayList("taskText").get(i)));
                }else{
                    savedItems.set(0, new TaskItem(false,
                            savedInstanceState.getStringArrayList("taskText").get(i)));
                }
            }
            listItems = savedItems;
        }

            adapter = new TaskItemAdapter(this, listItems);
            listview.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!text.getText().toString().equals("")) {
                    TaskItem newTask = new TaskItem(false, text.getText().toString());
                    listItems.add(newTask);
                    adapter.notifyDataSetChanged();
                    text.setText("");
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addButton.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CreateList.class);
                i.putExtra(listTitle.getText().toString(), listTitle.getText().toString());
                startActivity(i);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                String str_item = listview.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Clicked"+str_item, Toast.LENGTH_LONG).show();
            }
        });


    }

   @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        ArrayList<String> taskStatus = new ArrayList<String>();
        ArrayList<String> taskText = new ArrayList<String>();
        int size = listItems.size();
        for(int i = 0; i < size; i++){
            if(listItems.get(i).check){
                taskStatus.add("T");
            }else{
                taskStatus.add("F");
            }
            taskText.add(listItems.get(i).task);
        }
        savedInstanceState.putStringArrayList("taskStatus", taskStatus);
        savedInstanceState.putStringArrayList("taskText", taskStatus);


        super.onSaveInstanceState(savedInstanceState);

    }

}