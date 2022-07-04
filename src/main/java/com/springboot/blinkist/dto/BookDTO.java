package com.springboot.blinkist.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDTO {

    private int id;
    private String title;
    private int authorId;
    private int categoryId;
    private String image;


}
