package com.example.checklisttest.list_items;


import android.widget.Button;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.checklisttest.entities.Task;

@Entity(tableName = "task_item")
public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "check")
    private boolean check;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "checklistID")
    public String checklistID;

    @Ignore
    public Button delete;

    public TaskItem(boolean check, String task, String checklistID) {
        this.check = check;
        this.task = task;
    }

    public TaskItem(){}

    public boolean getCheck(){
        return this.check;
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public void setTask(String task){
        this.task = task;
    }

    public String getTask(){
        return this.task;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getChecklistID(){
        return checklistID;
    }
    public void setChecklistID(String checklistID){
        this.checklistID = checklistID;
    }

}
