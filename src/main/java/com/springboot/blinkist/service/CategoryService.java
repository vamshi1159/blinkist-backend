package com.springboot.blinkist.service;

import com.springboot.blinkist.dto.CategoryDTO;
import com.springboot.blinkist.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();


    void save(Category categoryDTO);
}

