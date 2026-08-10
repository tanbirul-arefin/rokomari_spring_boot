package com.rokomari.controller;

import com.rokomari.model.Book;
import com.rokomari.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // To show all books from repository
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listBooks",bookService.getAllBook());
        return "index";
    }

    // Show new book form for adding
    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "showNewBookForm";
    }

    // Save new book
    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    // Show form for updating
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "showNewBookForm";
    }

    // Delete book
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") long id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

//new add book function add



}
