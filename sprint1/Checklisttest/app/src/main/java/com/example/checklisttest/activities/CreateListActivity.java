package com.example.checklisttest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.checklisttest.databases.CheckListDatabase;
import com.example.checklisttest.list_items.ChecklistItem;
import com.example.checklisttest.list_adapters.ChecklistItemAdapter;
import com.example.checklisttest.R;

import java.util.ArrayList;

public class CreateListActivity extends AppCompatActivity {
    ListView createListView;
    EditText listName;
    Button addListButton;
    ArrayList<ChecklistItem> listItems;
    ChecklistItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        CheckListDatabase checkDB = Room.databaseBuilder(getApplicationContext(),
                CheckListDatabase.class, "checklist_db").build();
        listName = (EditText) findViewById(R.id.nameOfList);
        addListButton = findViewById(R.id.addlistbutton);
        createListView = (ListView) findViewById(R.id.listOfLists);
        listItems = new ArrayList<ChecklistItem>();
        adapter = new ChecklistItemAdapter(this, listItems);
        createListView.setAdapter(adapter);

        addListButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!listName.getText().toString().equals("")) {
                    ChecklistItem newItem = new ChecklistItem(listName.getText().toString());
                    listItems.add(newItem);
                    adapter.notifyDataSetChanged();
                    listName.setText("");
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addListButton.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

        createListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                /*String str_item = createListView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Clicked"+str_item, Toast.LENGTH_LONG).show();*/
                Intent i = new Intent(getApplicationContext(), CreateTasksActivity.class);
                i.putExtra("title", listItems.get(position).getChecklistName());
                startActivity(i);
            }
        });
    }


}