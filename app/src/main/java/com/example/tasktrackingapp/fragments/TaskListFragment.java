package com.example.tasktrackingapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasktrackingapp.viewmodel.TaskTrackingViewModel;

public class TaskListFragment extends Fragment {
    private TaskTrackingViewModel taskTrackingViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskTrackingViewModel = new ViewModelProvider(this).get(TaskTrackingViewModel.class);
    }
}
