package com.springboot.blinkist.controller;


import com.springboot.blinkist.entity.Book;
import com.springboot.blinkist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId(0);
        bookService.save(book);
        return book;
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        bookService.save(book);
        return bookService.findById(book.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return "deleted book with id "+id;
    }


}
