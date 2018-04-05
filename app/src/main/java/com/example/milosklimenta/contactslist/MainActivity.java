package com.example.milosklimenta.contactslist;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    RecyclerView rvContacts;

    ListAdapter adapter;
    RecyclerView.LayoutManager mManager;
    ArrayList<Contact> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicijalizacija();
        loadContacts();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                loadContacts();
            } else {
                Toast.makeText(this, "You need to grant permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            contactImport();
            // Android version is less than 6.0 or the permission is already granted.
//            rvContacts = findViewById(R.id.recyclerView);
            adapter = new ListAdapter(mList, this);
            rvContacts.setAdapter(adapter);
//            mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            rvContacts.setLayoutManager(mManager);
        }
    }


    public void inicijalizacija() {
        mList = new ArrayList<>();
        rvContacts = findViewById(R.id.recyclerView);
//        adapter = new ListAdapter(this);
//        rvContacts.setAdapter(adapter);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContacts.setLayoutManager(mManager);
    }

    public void contactImport() {

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String name;
        String phone = null;
        String image_url = "";
        Contact contact;

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext() && cursor != null) {


                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                image_url = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?", new String[]{id}, null);
                if (pCur.moveToNext() && pCur != null) {
                    phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                pCur.close();
                contact = new Contact(image_url, name, phone, id);
                mList.add(contact);
                Log.d("Testiranje", "size = " + mList.size());
            }
            cursor.close();


        }
    }
}


