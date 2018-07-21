package com.example.qq.dawd_lab05;

/**
 * Created by QQ on 3/29/2017.
 */

public class Book {
    private String code;
    private String title;
    private String type;
    private double price;
    private String author;

    public Book() {
    }

    public Book(String code, String title, String type, double price, String author) {
        this.code = code;
        this.title = title;
        this.type = type;
        this.price = price;
        this.author = author;
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

    @Override
    public String toString() {
        return this.code + " - " + this.title;
    }
}
