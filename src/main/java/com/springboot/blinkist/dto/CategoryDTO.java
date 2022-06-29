package com.springboot.blinkist.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CategoryDTO {
    private int id;
    private String label;
}
