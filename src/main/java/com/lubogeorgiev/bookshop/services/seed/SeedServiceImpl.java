package com.lubogeorgiev.bookshop.services.seed;

import com.lubogeorgiev.bookshop.domain.entities.Author;
import com.lubogeorgiev.bookshop.services.author.AuthorService;
import com.lubogeorgiev.bookshop.services.book.BookService;
import com.lubogeorgiev.bookshop.services.category.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.lubogeorgiev.bookshop.constants.FilePath.*;
import static java.nio.file.Files.readAllLines;

@Component
public class SeedServiceImpl implements SeedService{
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoriesService categoriesService;

    @Autowired
    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoriesService categoriesService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoriesService = categoriesService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!this.authorService.isDataSeeded()) {
            this.authorService.seedAuthors(readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
                    .stream()
                    .filter(str -> !str.isBlank())
                    .map(str -> {
                        String[] firstAndLastName = str.split(" ");
                        return Author.builder()
                                .firstName(firstAndLastName[0])
                                .lastName(firstAndLastName[1])
                                .build();
                    }).toList());
        }

    }

    @Override
    public void seedBooks() throws IOException {
        if (!this.bookService.isSeeded()){
            Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                    .stream()
                    .filter(str -> !str.isBlank())
                    .map(str -> {

                    })
        }
    }

    @Override
    public void seedCategories() {

    }
}
