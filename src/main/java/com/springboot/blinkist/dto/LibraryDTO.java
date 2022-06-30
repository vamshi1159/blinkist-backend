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
public class LibraryDTO {
    private int id;
    private String userName;
    private int bookId;
    private int statusId;

}
