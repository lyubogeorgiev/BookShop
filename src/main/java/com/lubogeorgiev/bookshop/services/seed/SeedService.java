package com.lubogeorgiev.bookshop.services.seed;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;
    void seedBooks() throws IOException;
    void seedCategories();

    default void seedAllData() throws IOException {
        seedAuthors();
        seedBooks();
        seedCategories();
    }
}
