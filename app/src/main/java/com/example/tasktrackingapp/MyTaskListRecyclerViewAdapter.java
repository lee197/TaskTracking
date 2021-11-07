package com.example.tasktrackingapp;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.databinding.FragmentItemBinding;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyTaskListRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskListRecyclerViewAdapter.ViewHolder> {

    private List<Item> mValues;

    public void setItemList(List<Item> list) {
        this.mValues = list;
    }

    @Override
    public @NotNull ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).uid));
        holder.mTitleView.setText(mValues.get(position).title);
        holder.mDesView.setText(mValues.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mTitleView;
        public final TextView mDesView;

        public Item mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mTitleView = binding.taskTitle;
            mDesView = binding.taskDes;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}