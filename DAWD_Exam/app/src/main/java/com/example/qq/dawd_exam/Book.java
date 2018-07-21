package com.example.qq.dawd_exam;

/**
 * Created by QQ on 4/14/2017.
 */

public class Book {
    private int BookId;
    private String Title;
    private int Price;

    public Book() {
    }

    public Book(int bookId, String title, int price) {
        BookId = bookId;
        Title = title;
        Price = price;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return BookId + "  " + Title + "\t\t" + Price;
    }
}
