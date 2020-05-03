package com.example.bookery;

public class Book {
    private String Title;
    private int BookId;
    private String P_Name;
    private String Author_Name;

    public String getTitle() {
        return Title;
    }
    public void setTitle(String name) {Title = name;}
    public int getBookId() {
        return BookId;
    }
    public void setBookId(int id) {
        BookId = id;
    }
    public String getP_Name() {
        return P_Name;
    }
    public void setP_Name(String name) {P_Name = name;}
    public String getAuthor_Name() {
        return Author_Name;
    }
    public void setAuthor_Name(String name) {Author_Name = name;}
}
