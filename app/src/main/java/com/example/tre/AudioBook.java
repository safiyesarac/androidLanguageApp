package com.example.tre;

public class AudioBook
{
    private Books[] books;

    public Books[] getBooks()
    {
        return books;
    }

    public void setBooks(Books[] books)
    {
        this.books = books;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [books = "+books+"]";
    }
}

