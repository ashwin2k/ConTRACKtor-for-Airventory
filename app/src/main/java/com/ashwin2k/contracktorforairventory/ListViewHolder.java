package com.ashwin2k.contracktorforairventory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView airport,itemsn,deadtime,deaddate;
    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }
    private RecyclerViewClickListener mListener;
    ImageView photo;
    public ListViewHolder(@NonNull View itemView,RecyclerViewClickListener listener) {
        super(itemView);
        airport=itemView.findViewById(R.id.airport_name);
        itemsn=itemView.findViewById(R.id.item_sno);
        deaddate=itemView.findViewById(R.id.deaddate);
        deadtime=itemView.findViewById(R.id.deadtime);
        itemView.setOnClickListener(this);
        mListener=listener;
    }

    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }

}
