package com.example.tasktrackingapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasktrackingapp.MyTaskListRecyclerViewAdapter;
import com.example.tasktrackingapp.R;
import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.placeholder.PlaceholderContent;
import com.example.tasktrackingapp.viewmodel.EditTaskViewModel;
import com.example.tasktrackingapp.viewmodel.TaskTrackingViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class TaskListFragment extends Fragment {

    private TaskTrackingViewModel mTaskTrackingViewModel;
    private final MyTaskListRecyclerViewAdapter adapter = new MyTaskListRecyclerViewAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskTrackingViewModel = new ViewModelProvider(this).get(TaskTrackingViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mTaskTrackingViewModel.getAllItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItemList(items);
            adapter.notifyDataSetChanged();

            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }
}