package com.mannmade.verizoncontactapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

/*
Please Note:
- Start by adding your contacts
- Single clicking a contact will allow you to edit
- Long clicking a contact will delete the contact and refresh the list automatically for you
- This program allows you to enter a blank contact (if this is undesired, please use delete to remove your contact
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionbar setup
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setLogo(R.mipmap.ic_verizon_icon);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_verizon_icon);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        }

        RecyclerView contactList = (RecyclerView) findViewById(R.id.contact_list);
        ImageButton addButton = (ImageButton) findViewById(R.id.add_contact);

        //Add new contact
        if(addButton != null){
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditContactActivity.class);
                    startActivity(intent);
                }
            });
        }

        //create new adapter and plug it into recycler view
        ContactAdapter contactAdapter = new ContactAdapter(ContactListSingleton.getInstance().getList());
        if(contactList != null){
            contactList.setLayoutManager(new LinearLayoutManager(this));
            contactList.setAdapter(contactAdapter);
        }
    }
}
