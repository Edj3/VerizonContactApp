package com.mannmade.verizoncontactapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Eg0 Jemima on 4/8/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    ArrayList<Contact> contacts;

    //constructor
    public ContactAdapter(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView firstName;
        public TextView lastName;
        public ImageView photoUrl;

        public ViewHolder(View v) {
            super(v);
            firstName = (TextView) v.findViewById(R.id.first_name);
            lastName = (TextView) v.findViewById(R.id.last_name);
            photoUrl = (ImageView) v.findViewById(R.id.contact_image);
        }
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        final ContactAdapter.ViewHolder myHolder = holder;
        holder.firstName.setText(contacts.get(position).firstName);
        holder.lastName.setText(contacts.get(position).lastName);
        if(contacts.get(position).photoUrl != null && !contacts.get(position).photoUrl.isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        URL url = new URL(contacts.get(index).photoUrl);
                        final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        Handler mainHandler = new Handler(myHolder.photoUrl.getContext().getMainLooper());
                        Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {
                                myHolder.photoUrl.setImageBitmap(bmp);
                            }
                        };
                        mainHandler.post(myRunnable);
                    }catch(Exception e){
                        Log.e(e.getClass().getName(), e.toString());
                    }
                }
            }).start();

        }

        //Add / Edit Contact
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditContactActivity.class);
                intent.putExtra("index", index);
                v.getContext().startActivity(intent);
            }
        });
        //Delete Contact
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ContactListSingleton.getInstance().getList().remove(index);
                notifyDataSetChanged(); //refresh the list so item gets removed
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
