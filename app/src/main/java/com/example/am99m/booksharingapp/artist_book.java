package com.example.am99m.booksharingapp;

/**
 * Created by am99m on 21-06-2018.
 */

public class artist_book {

    String book_name;
    String author_name;
    String price;
    String mobile;

    public artist_book() {

    }

    public artist_book(String book_name, String athor_name, String price,String mobile) {

        this.book_name = book_name;
        this.author_name = athor_name;
        this.price=price;
        this.mobile=mobile;
    }


    public String getBook_name()
    {
        return book_name;
    }

    public String getAuthor_name()
    {
        return author_name;
    }
    public String getPrice()
    {
        return price;
    }
    public String getMobile() {
        return  mobile;
    }


    public void setBook_name(String book_name)
    {
        this.book_name = book_name;
    }

    public void setAuthor_name(String author_name)
    {
        this.author_name = author_name;

    }
    public void setPrice(String price)
    {
        this.price=price;
    }
    public void setMobile(String mobile)
    {
        this.mobile=mobile;
    }

}
