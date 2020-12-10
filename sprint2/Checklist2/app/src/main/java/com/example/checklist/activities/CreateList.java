package com.example.checklist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checklist.ListNameDatabase;
import com.example.checklist.ListNameItem;
import com.example.checklist.R;
import com.example.checklist.TaskDatabase;
import com.example.checklist.adapters.ListNameAdapter;

import java.util.List;

//create list activity
public class CreateList extends AppCompatActivity implements View.OnClickListener {
    //INITIALIZE databases
    public static ListNameDatabase listNameDatabase;
    public static TaskDatabase taskDatabase;
    //INITIALIZE view items
    private ListView lv;
    private Button addlist;
    EditText etlistname;
    Drawable bg;
    //INITIALIZE list and adapter
    ListNameAdapter adapter;
    private List<ListNameItem> listNameItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlist);
        //set background image
        bg = getResources().getDrawable(R.drawable.listbg);
        getWindow().setBackgroundDrawable(bg);
        //set toolbar text color
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFF769\">" + getString(R.string.app_name)+ "</font>"));
        //build databases within activities
        taskDatabase = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class,"taskdb").allowMainThreadQueries().build();
        listNameDatabase= Room.databaseBuilder(getApplicationContext(),ListNameDatabase.class,"listdb").allowMainThreadQueries().build();
        //retrieve items in view
        addlist = (Button)findViewById(R.id.addlistbutton);
        etlistname = (EditText)findViewById(R.id.nameOfList);
        lv = (ListView) findViewById(R.id.listOfLists);

        //retrieve checklist names from database and set adapter
        listNameItems = CreateList.listNameDatabase.listNameDao().getMyData();
        adapter = new ListNameAdapter(this ,listNameItems);
        lv.setAdapter(adapter);
        //onclick methods
        addlist.setOnClickListener(this);
        //clicking item in listview will activite Tasklist acvitivity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), listNameItems.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Tasklist.class);
                i.putExtra("title", listNameItems.get(position).getName());
                i.putExtra("id", listNameItems.get(position).getId());
                startActivity(i);
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id){
            case R.id.addlistbutton:{
                String listname = etlistname.getText().toString();

                ListNameItem listNameItem = new ListNameItem();

                listNameItem.setName(listname);
                listNameItems.add(listNameItem);
                listNameDatabase.listNameDao().addData(listNameItem);
                //listNameItems = listNameDatabase.listNameDao().getMyData();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"List Added",Toast.LENGTH_LONG).show();
                etlistname.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(findViewById(R.id.addlistbutton).getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        }
    }

}