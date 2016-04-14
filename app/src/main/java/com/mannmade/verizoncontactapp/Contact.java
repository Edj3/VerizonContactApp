package com.mannmade.verizoncontactapp;

/**
 * Created by Eg0 Jemima on 4/8/2016.
 */
//Since class is public, we do not need direct getters and setters
public class Contact{
    String firstName;
    String lastName;
    String photoUrl;

    public Contact(String firstName, String lastName, String url){
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = url;
    }
}
