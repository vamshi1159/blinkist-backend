package com.springboot.blinkist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "label")
    private String label;
}
