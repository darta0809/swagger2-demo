package com.vincent.swagger2.demo.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;

    @Column
    private String name;

    @Column
    private String author;
}
