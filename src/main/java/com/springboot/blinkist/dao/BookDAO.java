package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Book;
import com.springboot.blinkist.exception.NotFoundException;

import java.util.List;

public interface BookDAO {
    List<Book> findAll(String id,String author,String category);

    Book findById(int id);

    Book save(Book book) throws NotFoundException;

    void deleteById(int id);
}
