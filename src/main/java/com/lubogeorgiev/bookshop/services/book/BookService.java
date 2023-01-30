package com.lubogeorgiev.bookshop.services.book;

import com.lubogeorgiev.bookshop.domain.entities.Author;
import com.lubogeorgiev.bookshop.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks(List<Book> books);

    boolean isSeeded();

    List<Book> findAllByReleaseDate(LocalDate date);

}
