package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.BreakIterator;
import java.util.List;

public class AdapterPet extends RecyclerView.Adapter<AdapterPet.ViewHolder> {


    private List<Pet> mData;
    private LayoutInflater mInflater;
    private Context context;

    private final AdapterPet.ItemClickListener mClickListener = new ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Pet rest = mData.get(position);
            Intent i = new Intent(context,PetDetailsActivity.class);
            i.putExtra("rest", rest);


            context.startActivity(i);
        }
    };

    // data is passed into the constructor
    AdapterPet(Context context, List<Pet> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public AdapterPet.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row, parent, false);
        return new AdapterPet.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(AdapterPet.ViewHolder holder, int position) {
        Pet rest = mData.get(position);
        holder.tvName.setText(rest.getCategory().toString());
        Picasso.get().load(rest.getPhoto()).into(holder.ivPhoto);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrol    led off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.detailsrow);
            ivPhoto = itemView.findViewById(R.id.imageViewrow);
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
    /*
    void setClickListener(AdapterPet.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
**/
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
   }



