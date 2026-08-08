package com.rokomari.service;

import com.rokomari.model.Book;
import com.rokomari.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService{

    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
