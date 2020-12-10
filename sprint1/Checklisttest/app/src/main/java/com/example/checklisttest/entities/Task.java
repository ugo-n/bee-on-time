package com.example.checklisttest.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey
    private int list_name;

    @ColumnInfo(name = "task_list")
    private ArrayList<String> taskList;

    @ColumnInfo(name = "checklistID")
    private int checklistID;

}
