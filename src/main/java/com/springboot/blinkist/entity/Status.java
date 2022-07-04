package com.springboot.blinkist.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "label")
    private String label;
}
