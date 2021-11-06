package com.example.tasktrackingapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tasktrackingapp.models.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    LiveData<List<Item>> getAll();

    @Update
    void updateItems(Item... items);

    @Insert
    void insert(Item... items);

    @Delete
    void delete(Item item);
}
