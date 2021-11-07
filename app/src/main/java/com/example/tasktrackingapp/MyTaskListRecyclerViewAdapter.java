package com.example.tasktrackingapp;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.databinding.FragmentItemBinding;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class MyTaskListRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskListRecyclerViewAdapter.ViewHolder> {

    private List<Item> mValues;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setItemList(List<Item> list) {
        this.mValues = list;
    }

    public MyTaskListRecyclerViewAdapter( OnItemClickListener listener) {
        this.listener = listener;
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

        holder.bind(mValues.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
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

        public void bind(final Item item, final OnItemClickListener listener) {
            mIdView.setOnClickListener(v -> listener.onItemClick(item));
            mTitleView.setOnClickListener(v -> listener.onItemClick(item));
            mDesView.setOnClickListener(v -> listener.onItemClick(item));
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}