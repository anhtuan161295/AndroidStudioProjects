package com.example.qq.bookapplication;

/**
 * Created by QQ on 4/5/2017.
 */

public class Book {
    private int code;
    private String title;
    private double price;
    private String author;

    public Book() {
    }

    public Book(int code, String title, double price, String author) {
        this.code = code;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return code + " - " + title + " - " + price;
    }
}
