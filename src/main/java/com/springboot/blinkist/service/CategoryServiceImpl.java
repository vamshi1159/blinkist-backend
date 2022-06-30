package com.springboot.blinkist.service;

import com.springboot.blinkist.converter.CategoryConverter;
import com.springboot.blinkist.dao.CategoryDAO;
import com.springboot.blinkist.dto.CategoryDTO;
import com.springboot.blinkist.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    @Transactional
    public List<CategoryDTO> findAll() {
        return CategoryConverter.entityToDTO(categoryDAO.findAll());
    }


    @Override
    @Transactional
    public void save(Category categoryDTO) {
            categoryDAO.save(categoryDTO);
    }
}
