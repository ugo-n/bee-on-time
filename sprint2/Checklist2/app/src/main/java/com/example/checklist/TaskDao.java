package com.example.checklist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    public void addData(TaskItem taskItem);

    @Query("select * from tasklist where listID = :listID")
    public List<TaskItem> getMyData(int listID);

    @Delete
    public void delete(TaskItem taskItem);

    @Query("delete from tasklist where listID = :listID")
    public void nukeTable(int listID);

    @Update
    public void update(TaskItem taskItem);
}
