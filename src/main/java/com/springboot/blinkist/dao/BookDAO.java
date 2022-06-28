package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();
    Book findById(int id);
    void save(Book book);
    void deleteById(int id);
}
