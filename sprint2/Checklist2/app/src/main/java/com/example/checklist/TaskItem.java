package com.example.checklist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasklist")
public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "listID")
    private int listID;

    @ColumnInfo(name = "isChecked")
    private boolean isChecked;

    @ColumnInfo(name = "tasktext")
    private String tasktext;

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public int getListID(){ return listID; }
    public void setListID(int listID){ this.listID = listID; }

    public void setChecked(boolean checked) { isChecked = checked; }
    public boolean isChecked() { return isChecked; }

    public String getTasktext() { return tasktext; }
    public void setTasktext(String tasktext) { this.tasktext = tasktext; }
}