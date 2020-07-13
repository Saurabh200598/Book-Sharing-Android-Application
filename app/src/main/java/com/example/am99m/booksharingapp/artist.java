package com.example.am99m.booksharingapp;

/**
 * Created by am99m on 21-06-2018.
 */

public class artist {

    String name;
    String mobile;
    String password;

    public artist() {

    }

    public artist(String name, String mobile, String password) {

        this.name = name;
        this.mobile = mobile;
        this.password=password;
    }


    public String getName()
    {
        return name;
    }

    public String getMobile()
    {
        return mobile;
    }
    public String getPassword()
    {
        return password;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;

    }
    public void setPassword(String password)
    {
        this.password=password;
    }

}
