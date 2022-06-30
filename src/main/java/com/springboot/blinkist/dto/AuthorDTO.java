package com.springboot.blinkist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AuthorDTO {

    private int id;
    private String firstName;
    private String lastName;

}
