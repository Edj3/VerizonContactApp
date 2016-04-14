package com.mannmade.verizoncontactapp;

import java.util.ArrayList;

/**
 * Created by Eg0 Jemima on 4/14/2016.
 */
public class ContactListSingleton {
    //member variables and constructor need to be private for singletons! Only allow others to access needed getters
    private ArrayList<Contact> contactList;
    private static ContactListSingleton mInstance = null;

    //private constructor
    private ContactListSingleton(){
        this.contactList = new ArrayList<>();
    }

    //static function to get single instance of Singleton
    public static ContactListSingleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new ContactListSingleton();
        }
        return mInstance;
    }

    //getter for list
    public ArrayList<Contact> getList(){
        return contactList;
    }
}
