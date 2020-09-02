package com.ashwin2k.contracktorforairventory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ListViewHolder> {
    ArrayList<EquimentData> li;
    interface clicklisten{
        void doOnClick(int pos);
    }
    clicklisten delegate;
    public RecyclerAdapter(ArrayList<EquimentData> list,clicklisten d){
        li=list;
        delegate=d;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_layout,parent,false);
        ListViewHolder holder=new ListViewHolder(view, new ListViewHolder.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("CLICKK",position+"");
                delegate.doOnClick(position);

            }
        });

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            holder.deaddate.setText(li.get(position).getDatedue());
            holder.airport.setText(li.get(position).getAirport());
            holder.itemsn.setText(li.get(position).getItem());
            holder.deadtime.setText(li.get(position).getTimedue());


    }

    @Override
    public int getItemCount() {
        return li.size();
    }
}
