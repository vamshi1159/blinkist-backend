package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();

    Category findById(int id);

    void save(Category categoryDTO);
}
