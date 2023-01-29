package com.lubogeorgiev.bookshop.services.author;

import com.lubogeorgiev.bookshop.domain.entities.Author;

import java.util.List;

public interface AuthorService {

    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();

    Author getRandomAuthor();
}
