package com.springboot.blinkist.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class AuthorDTO {

    private int id;
    private String firstName;
    private String lastName;

}
