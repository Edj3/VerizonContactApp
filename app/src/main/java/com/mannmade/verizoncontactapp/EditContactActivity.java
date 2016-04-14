package com.mannmade.verizoncontactapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditContactActivity extends AppCompatActivity {
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        index = getIntent().getIntExtra("index", -1);

        //actionbar setup
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setLogo(R.mipmap.ic_verizon_icon);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_verizon_icon);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            if(index > -1 && ContactListSingleton.getInstance().getList().get(index).firstName != null && ContactListSingleton.getInstance().getList().get(index).lastName != null){
                getSupportActionBar().setTitle(ContactListSingleton.getInstance().getList().get(index).firstName + " " + ContactListSingleton.getInstance().getList().get(index).lastName);
            }else{
                getSupportActionBar().setTitle(getResources().getString(R.string.contact_add_edit_title));
            }
        }

        final EditText fEdit = (EditText) findViewById(R.id.first_name_edit);
        final EditText lEdit = (EditText) findViewById(R.id.last_name_edit);
        final EditText uEdit = (EditText) findViewById(R.id.photo_edit);
        Button saveButton = (Button) findViewById(R.id.save_button);

        if(index > -1){

            if(fEdit != null){
                fEdit.setText(ContactListSingleton.getInstance().getList().get(index).firstName);
            }

            if(lEdit != null){
                lEdit.setText(ContactListSingleton.getInstance().getList().get(index).lastName);
            }

            if(uEdit != null){
                uEdit.setText(ContactListSingleton.getInstance().getList().get(index).photoUrl);
            }

            if(saveButton != null){
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(fEdit != null){
                            ContactListSingleton.getInstance().getList().get(index).firstName = fEdit.getText().toString();
                        }

                        if(lEdit != null){
                            ContactListSingleton.getInstance().getList().get(index).lastName = lEdit.getText().toString();
                        }

                        if(uEdit != null){
                            ContactListSingleton.getInstance().getList().get(index).photoUrl = uEdit.getText().toString();
                        }

                        finish();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);

                    }
                });
            }

        }else{

            if(saveButton != null){
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(fEdit != null && lEdit != null && uEdit != null){
                            ContactListSingleton.getInstance().getList().add(new Contact(fEdit.getText().toString(), lEdit.getText().toString(), uEdit.getText().toString()));
                        }

                        finish();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);

                    }
                });
            }

        }
    }
}
