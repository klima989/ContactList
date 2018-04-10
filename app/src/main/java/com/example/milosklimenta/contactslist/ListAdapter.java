package com.example.milosklimenta.contactslist;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    public static List<Contact> mList;
    private Context mContext;
    private int focusedItem = 0;

    public ListAdapter(List<Contact> list, Context mContext) {
        this.mList = list;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.recycler_item_view, parent, false);
        ListViewHolder holder = new ListViewHolder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        final Contact contact = mList.get(position);

        final TextView contact_name = holder.contactName;

        final  ImageView contact_image = holder.contactImage;

        if(contact.getContactImage() == null){
            contact_image.setImageResource(R.drawable.avatar);
        }else{
            Picasso.with(mContext)
                    .load(contact.getContactImage())
                    .into(contact_image);
        }


        contact_name.setText(contact.getContactName());
        contact_name.setContentDescription(contact.getContactName());

        holder.linearLayout/*contactName*/.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContactInfoActivity.class);
                intent.putExtra("oneContact", contact);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
