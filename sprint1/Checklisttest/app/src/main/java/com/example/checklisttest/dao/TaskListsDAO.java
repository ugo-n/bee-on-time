package com.example.checklisttest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.checklisttest.list_items.ChecklistItem;

import java.util.List;
@Dao
public interface TaskListsDAO {

    /*@Query("SELECT* FROM task WHERE checklistID = :checklistID")
    List<ChecklistItem> getAllByCheckList(int checklistID);*/
    @Insert
    void insert(ChecklistItem checklist);

    @Delete
    void delete(ChecklistItem checklist);

    @Update
    void update(ChecklistItem checklist);

}
