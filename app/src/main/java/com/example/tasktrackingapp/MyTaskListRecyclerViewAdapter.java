package com.example.tasktrackingapp;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tasktrackingapp.models.Item;
import com.example.tasktrackingapp.databinding.FragmentItemBinding;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class MyTaskListRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskListRecyclerViewAdapter.ViewHolder> {

    private List<Item> mValues;
    private final OnItemClickListener itemListener;
    private final OnSwitchClickListener switchListener;
    private final OnDeleteListener deleteListener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public interface OnSwitchClickListener {
        void onSwitchClick(Item item);
    }

    public interface OnDeleteListener {
        void onCellDeleted(Item item);
    }

    public void setItemList(List<Item> list) {
        this.mValues = list;
    }

    public MyTaskListRecyclerViewAdapter( OnItemClickListener listener,
                                          OnSwitchClickListener switchListener,
                                          OnDeleteListener deleteListener
                                          ) {
        this.itemListener = listener;
        this.switchListener = switchListener;
        this.deleteListener = deleteListener;
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
        if (mValues.get(position).status == null) {
            holder.mStateSwitch.setChecked(false);
        } else {
            holder.mStateSwitch.setChecked(mValues.get(position).status.equals("Done"));
        }
        holder.bind(mValues.get(position), switchListener);
        holder.bind(mValues.get(position), itemListener);
        holder.bind(mValues.get(position), deleteListener);
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
        public final Switch mStateSwitch;
        public Item mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mTitleView = binding.taskTitle;
            mDesView = binding.taskDes;
            mStateSwitch = binding.statusSwitch;
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            mTitleView.setOnClickListener(v -> listener.onItemClick(item));
            mDesView.setOnClickListener(v -> listener.onItemClick(item));
        }

        public void bind(final Item item, final OnSwitchClickListener listener) {
            mStateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    item.status = "Done";
                } else {
                    item.status = "Ongoing";
                }
                listener.onSwitchClick(item);
            });
        }

        public void bind(final Item item, final OnDeleteListener listener) {
            mIdView.setOnClickListener(v -> listener.onCellDeleted(item));
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}