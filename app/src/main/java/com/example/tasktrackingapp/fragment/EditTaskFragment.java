package com.example.tasktrackingapp.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tasktrackingapp.databinding.EditTaskFragmentBinding;
import com.example.tasktrackingapp.viewmodel.EditTaskViewModel;

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

            if (title != null && !title.toString().equals("")) {
                if (des == null) {
                    mViewModel.insert(title.toString(), "");
                } else {
                    mViewModel.insert(title.toString(), des.toString());
                }
            } else {
                Toast.makeText(getActivity(),"Please input task title",Toast.LENGTH_SHORT).show();
                return;
            }

            NavController nav = NavHostFragment.findNavController(this);
            nav.popBackStack();
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