package com.example.tasktrackingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.repository.TaskTrackingRepository;

import java.util.List;

public class TaskTrackingViewModel extends AndroidViewModel {
    private final TaskTrackingRepository repository;
    public LiveData<List<Item>> getAllItems;

    public TaskTrackingViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new TaskTrackingRepository(application);
        getAllItems = repository.getAllItems();
    }

    public void insert(Item item) {
        repository.insert(item);
    }

    public void delete(Item item) {
        repository.insert(item);
    }

    public void update(Item item) {
        repository.update(item);
    }

    public LiveData<List<Item>> getAllItems() {
        return getAllItems;
    }
}
