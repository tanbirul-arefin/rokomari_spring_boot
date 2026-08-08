package com.rokomari.service;

import com.rokomari.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();

    public void saveBook(Book book);
}
