package com.springboot.blinkist.converter;

import com.springboot.blinkist.dto.CategoryDTO;
import com.springboot.blinkist.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {


    private CategoryConverter() {

    }

    public static CategoryDTO entityToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setLabel(category.getLabel());
        return categoryDTO;
    }

    public static List<CategoryDTO> entityToDTO(List<Category> categories) {
        return categories.stream().map(CategoryConverter::entityToDTO).collect(Collectors.toList());
    }
}
