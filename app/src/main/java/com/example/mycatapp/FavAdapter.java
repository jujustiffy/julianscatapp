package com.example.mycatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FaveVH>{
    private ArrayList<Cat> favList;
    private Context favContext;
    private OnItemClickListener favListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        favListener = listener;
    }

    public FavAdapter(Context context, ArrayList<Cat> list) {
        favList = list;
        favContext = context;
    }

    @NonNull
    @Override
    public FaveVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourites_item, parent, false);
        return new FaveVH(v, favListener);
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FaveVH holder, int position) {
        Cat favItem = favList.get(position);
        String favName = favItem.getName();

        holder.favName.setText(favName);

    }

    public class FaveVH extends RecyclerView.ViewHolder {
        public TextView favName;
        public Button deleteFav;
        public FaveVH(View itemView, final OnItemClickListener listener) {
            super(itemView);
            favName = itemView.findViewById(R.id.favourite_name);
            deleteFav = itemView.findViewById(R.id.favourite_delete);
            deleteFav.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }


    }

}
