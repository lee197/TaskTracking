package com.example.tasktrackingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.repository.TaskTrackingRepository;

import java.util.List;

public class EditTaskViewModel extends AndroidViewModel {
    private final TaskTrackingRepository repository;
    public LiveData<List<Item>> getAllItems;

    public EditTaskViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new TaskTrackingRepository(application);
        getAllItems = repository.getAllItems();
    }

    public void insert(String title, String des) {
        Item item = new Item();
        item.description = des;
        item.title = title;
        repository.insert(item);
    }

    public void update(Item item) {
        repository.update(item);
    }
}

