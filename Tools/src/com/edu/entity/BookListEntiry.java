package com.edu.entity;

import java.util.List;

public class BookListEntiry {

    private int count;
    private int start;
    private int total;
    private List<BookInfoEntiry> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BookInfoEntiry> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfoEntiry> books) {
        this.books = books;
    }
}
