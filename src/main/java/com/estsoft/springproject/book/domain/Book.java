package com.estsoft.springproject.book.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    String id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String name;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String author;
}
