package com.springboot.blinkist.service;

import com.springboot.blinkist.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

}

