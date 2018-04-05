package com.example.milosklimenta.contactslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewHolder extends RecyclerView.ViewHolder {


    protected TextView contactName;
    protected View linearLayout;

    public ListViewHolder(View itemView) {
        super(itemView);

        linearLayout = itemView;

        contactName = itemView.findViewById(R.id.contactName);


        itemView.setClickable(true);

    }


}
