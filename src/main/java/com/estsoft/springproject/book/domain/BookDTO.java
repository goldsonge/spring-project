package com.estsoft.springproject.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDTO {
    private String id;
    private String name;
    private String author;

    public BookDTO(Book book) {
        id = book.id;
        name = book.name;
        author = book.author;
    }
}
