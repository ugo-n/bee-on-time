package com.example.checklisttest.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.checklisttest.dao.TaskListsDAO;
import com.example.checklisttest.list_items.ChecklistItem;
import com.example.checklisttest.list_items.TaskItem;

@Database(entities = {TaskItem.class, ChecklistItem.class}, version = 1, exportSchema = false)
public abstract class TaskListDatabase extends RoomDatabase {
    public abstract TaskListsDAO taskListsDAO();
}
