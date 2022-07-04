package com.springboot.blinkist.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LibraryDTO {
    private int id;
    private String userName;
    private int bookId;
    private int statusId;

}
