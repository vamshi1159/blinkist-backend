package com.springboot.blinkist.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LibraryDetailedDTO {
    private int id;
    private String userName;
    private String bookTitle;
    private String author;
    private String category;
    private String status;


}
