package com.example.checklist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListNameItem.class}, version = 1)
public abstract class ListNameDatabase extends RoomDatabase{
    public abstract ListNameDao listNameDao();
}
