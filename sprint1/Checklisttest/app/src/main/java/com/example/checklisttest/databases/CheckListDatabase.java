package com.example.checklisttest.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.checklisttest.dao.ListNamesDAO;
import com.example.checklisttest.dao.TaskListsDAO;
import com.example.checklisttest.list_items.ChecklistItem;

@Database(entities = {ChecklistItem.class}, version = 1)
public abstract class CheckListDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "checklists.db";
    private  static CheckListDatabase mCheckListDatabase;


    public abstract ListNamesDAO listNamesDAO();
    public abstract TaskListsDAO taskListsDAO();


}
