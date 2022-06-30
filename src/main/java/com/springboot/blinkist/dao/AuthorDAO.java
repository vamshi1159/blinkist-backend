package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> findAll(String id);

    Author findById(int id);

    Author save(Author author);

    void deleteById(int id);
}
