package com.springboot.blinkist.controller;

import com.springboot.blinkist.dto.AuthorDTO;
import com.springboot.blinkist.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAuthors(@RequestParam(value = "id", defaultValue = "") String id) {
        return authorService.findAll(id);
    }

    /**  implemented
    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable int id) {
        return authorService.findById(id);
    }
    */

    @PostMapping
    public AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.save(authorDTO);

    }

    @PutMapping
    public AuthorDTO updateAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.save(authorDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        authorService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("deleted author with id" + id);
    }
}
