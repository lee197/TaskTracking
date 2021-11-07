package com.example.tasktrackingapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasktrackingapp.databinding.EditTaskFragmentBinding;

public class EditTaskFragment extends Fragment {

    private EditTaskViewModel mViewModel;
    private EditTaskFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = EditTaskFragmentBinding.inflate(inflater, container, false);
        binding.confirmButton.setOnClickListener(v -> {

            Editable des = binding.descriptionInput.getText();
            Editable title = binding.titleInput.getText();

            if (title != null) {
                if (des == null) {
                    mViewModel.insert(title.toString(), "");
                } else {
                    mViewModel.insert(title.toString(), des.toString());
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditTaskViewModel.class);
        // TODO: Use the ViewModel
    }

}