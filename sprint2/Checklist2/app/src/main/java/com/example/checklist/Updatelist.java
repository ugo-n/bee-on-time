package com.example.checklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.checklist.activities.CreateList;

public class Updatelist extends AppCompatActivity {
    Intent intent;
    EditText etlistname;
    private Button cancel;
    private Button confirm;
    Drawable bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatelist);
        //set background image and toolbar text color
        bg = getResources().getDrawable(R.drawable.listbg);
        getWindow().setBackgroundDrawable(bg);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFF769\">" + getString(R.string.app_name)+ "</font>"));
        //get intent with list id
        intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        //retrieve objects from view
        cancel = findViewById(R.id.cancel_btn);
        confirm = findViewById(R.id.confirm_btn);
        etlistname = findViewById(R.id.newname);
        //confirm button to update list name
        confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!etlistname.getText().toString().equals("")) {
                    //create list name item with new list name, giving it the id of item being edited
                    ListNameItem newName = new ListNameItem();
                    String name = etlistname.getText().toString();
                    newName.setId(id);
                    newName.setName(name);

                    //update database with new list name
                    CreateList.listNameDatabase.listNameDao().update(newName);

                    //return to checklist
                    Intent i = new Intent(getApplicationContext(), CreateList.class);
                    i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
                }
                //closes keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(confirm.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //return to createlist activity
                Intent i = new Intent(getApplicationContext(), CreateList.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                //closes keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(confirm.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

    }
}