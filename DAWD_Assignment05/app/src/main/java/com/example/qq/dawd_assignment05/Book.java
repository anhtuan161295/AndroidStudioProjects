package com.example.qq.dawd_assignment05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by QQ on 3/29/2017.
 */

public class Book {
    private String code;
    private String title;
    private String type;
    private double price;
    private String author;
    private String img;

    public Book() {
    }

    public Book(String code, String title, String type, double price, String author, String img) {
        this.code = code;
        this.title = title;
        this.type = type;
        this.price = price;
        this.author = author;
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return this.code + " - " + this.title;
    }
}
