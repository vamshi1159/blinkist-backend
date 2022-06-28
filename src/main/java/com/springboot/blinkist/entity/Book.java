package com.springboot.blinkist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NonNull
    private  String title;

    @Column(name = "author")
    @NonNull
    private String author;

    @Column(name = "image")
    @NonNull
    private String image;

    @Column(name = "category")
    @NonNull
    private String category;

    @Column(name = "status")
    @NonNull
    private String status;
}
