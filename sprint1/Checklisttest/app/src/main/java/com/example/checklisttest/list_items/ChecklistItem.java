package com.example.checklisttest.list_items;

import android.graphics.drawable.Icon;
import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "checklists")
public class ChecklistItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "checklistID")
    public int checklistID;
    //public Bitmap checklistType;

    @ColumnInfo(name = "checklist_name")
    private String checklistName;

    public ChecklistItem(String checklistName) {
        //this.checklistType = checklistType;
        this.checklistName = checklistName;

    }
    public ChecklistItem(){
    }
    public String getChecklistName() {
        return checklistName;
    }

    public void setChecklistName(String checklistName){

        this.checklistName = checklistName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


