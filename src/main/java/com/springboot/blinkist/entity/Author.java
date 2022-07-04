package com.springboot.blinkist.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


}
