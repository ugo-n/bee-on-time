package com.example.checklisttest;


import android.widget.Button;

public class TaskItem {
    public boolean check;
    public String task;
    public Button delete;

    public TaskItem(boolean check, String task) {
        this.check = check;
        this.task = task;
    }
}
