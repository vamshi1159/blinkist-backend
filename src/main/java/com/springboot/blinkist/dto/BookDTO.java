package com.springboot.blinkist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {

    private int id;
    private String title;
    private int authorId;
    private int categoryId;
    private String image;


}
