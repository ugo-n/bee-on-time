package com.example.checklist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskItem.class, ListNameItem.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
