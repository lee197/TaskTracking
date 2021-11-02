package com.example.tasktrackingapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.room.AppDatabase;
import com.example.tasktrackingapp.room.ItemDao;

import java.util.List;

public class TaskTrackingRepository {

    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;

    public TaskTrackingRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        itemDao = database.itemDao();
        allItems = itemDao.getAll();
    }

    public void insert(Item item) {
        new InsertItemAsyncTask(itemDao).execute(item);
    }
    public void update(Item item) {
        new UpdateItemAsyncTask(itemDao).execute(item);
    }
    public void delete(Item item) {
        new DeleteItemAsyncTask(itemDao).execute(item);
    }
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }


    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private final ItemDao itemDao;
        private InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }
    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private final ItemDao itemDao;
        private UpdateItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.updateItems(items[0]);
            return null;
        }
    }
    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private final ItemDao itemDao;
        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }
}
