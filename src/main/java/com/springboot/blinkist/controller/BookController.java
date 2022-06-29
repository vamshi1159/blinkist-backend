package com.springboot.blinkist.controller;


import com.springboot.blinkist.dto.BookDTO;
import com.springboot.blinkist.dto.BookDetailedDTO;
import com.springboot.blinkist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDetailedDTO> getBooks(@RequestParam(value = "id", required = false, defaultValue = "") String id, @RequestParam(value = "author", required = false, defaultValue = "") String author, @RequestParam(value = "category", required = false, defaultValue = "") String category) {
        return bookService.findByQuery(id, author, category);
    }

    /** ** implemented in getBooks **
    @GetMapping("/{id}")
    public BookDetailedDTO getBook(@PathVariable int id) {

        BookDetailedDTO bookDetailedDTO =bookService.findById(id);
        if(bookDetailedDTO == null)
        {
            System.out.println("abc");
        }
        return bookDetailedDTO;
    }
    */
    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO book) {
        book.setId(0);
        return bookService.save(book);
    }

    @PutMapping
    public BookDTO updateBook(@RequestBody BookDTO book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted author with id" + id);
    }


}
