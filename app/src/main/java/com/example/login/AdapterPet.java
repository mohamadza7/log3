package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class AdapterPet extends RecyclerView.Adapter<AdapterPet.ViewHolder> {

    private List<Pet> mData;
    private LayoutInflater mInflater;
    private  AdapterPet.ItemClickListener mClickListener;

    // data is passed into the constructor
    AdapterPet(Context context, List<Pet> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public  AdapterPet.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row, parent, false);
        return new  AdapterPet.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder( AdapterPet.ViewHolder holder, int position) {
        Pet rest = mData.get(position);
        holder.tvkind.setText(rest.getName());
        //holder.ivPhoto.setImageDrawable(rest.getPhoto());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public BreakIterator tvkind;
        TextView tvName;
        ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.spkind);
            ivPhoto = itemView.findViewById(R.id.ivphotoPetAdd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Pet getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener( AdapterPet.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
