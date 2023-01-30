package com.lubogeorgiev.bookshop.services.book;

import com.lubogeorgiev.bookshop.domain.entities.Book;
import com.lubogeorgiev.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public boolean isSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public List<Book> findAllByReleaseDate(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date).orElseThrow(NoSuchElementException::new);
    }
}
