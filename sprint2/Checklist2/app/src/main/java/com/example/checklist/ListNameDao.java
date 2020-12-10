package com.example.checklist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListNameDao {
    @Insert
    public void addData(ListNameItem listNameItem);

    @Query("select * from listnamelist")
    public List<ListNameItem> getMyData();

    @Query("select * from listnamelist where id = :id")
    public ListNameItem getListName(int id);

    @Delete
    public void delete(ListNameItem listNameItem);

    @Query("DELETE from listnamelist")
    public void nukeTable();

    @Update
    public void update(ListNameItem listNameItem);
}
