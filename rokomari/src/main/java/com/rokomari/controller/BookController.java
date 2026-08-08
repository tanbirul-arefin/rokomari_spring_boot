package com.rokomari.controller;

import com.rokomari.model.Book;
import com.rokomari.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


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

    //
    @GetMapping("/showNewBookForm")
    public String view(Model model){
        Book b1 = new Book();

        model.addAttribute("book",b1);

        return "showNewBookForm";
    }



    //Creating form to insert book details
    @PostMapping("/saveBook")
    public String saveBook(Model model , Book book) {

        bookService.saveBook(book);

        //model.addAttribute("message","Book saved successfully");

        //ra.asValue("message","Book saved successfully");


//missing msql
        return "redirect:/";
    }





}
