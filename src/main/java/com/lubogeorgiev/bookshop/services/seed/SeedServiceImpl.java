package com.lubogeorgiev.bookshop.services.seed;

import com.lubogeorgiev.bookshop.domain.entities.Author;
import com.lubogeorgiev.bookshop.domain.entities.Book;
import com.lubogeorgiev.bookshop.domain.entities.Category;
import com.lubogeorgiev.bookshop.domain.enums.AgeRestriction;
import com.lubogeorgiev.bookshop.domain.enums.EditionType;
import com.lubogeorgiev.bookshop.services.author.AuthorService;
import com.lubogeorgiev.bookshop.services.book.BookService;
import com.lubogeorgiev.bookshop.services.category.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
            this.bookService.seedBooks(Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                    .stream()
                    .map(line -> line.split("\\s+"))
                    .map(line -> {
                        Author author = this.authorService.getRandomAuthor();

                        EditionType editionType = EditionType.values()[Integer.parseInt(line[0])];

                        LocalDate releaseDate = LocalDate.parse(line[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

                        int copies = Integer.parseInt(line[2]);

                        BigDecimal price = new BigDecimal(line[3]);

                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(line[4])];

                        String title = Arrays.stream(line).skip(5).collect(Collectors.joining(" "));

                        Set<Category> categories = this.categoriesService.getRandomCategories();

//                        Book book = new Book(title, editionType, price, releaseDate,
//                                ageRestriction, author, categories, copies);

                        return Book.builder()
                                .title(title)
                                .editionType(editionType)
                                .price(price)
                                .releaseDate(releaseDate)
                                .ageRestriction(ageRestriction)
                                .author(author)
                                .categories(categories)
                                .copies(copies)
                                .build();
                    })
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (!this.categoriesService.isCategorySeeded()){
            this.categoriesService.seedCategories(
                    Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                            .stream()
                            .filter(str -> !str.isBlank())
                            .map(str -> Category.builder().name(str).build())
                            .collect(Collectors.toList())
            );
        }
    }
}
