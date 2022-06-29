package com.springboot.blinkist.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookDetailedDTO {

    private int id;
    private String title;
    private String author;
    private String category;
    private String image;
}
