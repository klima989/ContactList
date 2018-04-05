package com.example.milosklimenta.contactslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Locale;

public class ContactInfoActivity extends Activity {

    ImageView mImage;
    TextView mName, mNumber;
    Context mContext;
    TextToSpeech t1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info_activity);
        inicijalizacija();

        Intent i = getIntent();
        final int position;
        position = i.getIntExtra("oneContact", 0);
        mName.setText(ListAdapter.mList.get(position).getContactName());
        mNumber.setText(ListAdapter.mList.get(position).getContactNumber());

        if (ListAdapter.mList.get(position).getContactImage() != null) {
            Picasso.with(getApplicationContext())
                    .load(ListAdapter.mList.get(position).getContactImage())
                    .into(mImage);
        }

        mName.setContentDescription(ListAdapter.mList.get(position).getContactName());
        mNumber.setContentDescription(ListAdapter.mList.get(position).getContactNumber());


    }

    public void inicijalizacija() {
        mImage = findViewById(R.id.contactImageInfo);
        mName = findViewById(R.id.contactNameInfo);
        mNumber = findViewById(R.id.contactNumberInfo);
    }

}
