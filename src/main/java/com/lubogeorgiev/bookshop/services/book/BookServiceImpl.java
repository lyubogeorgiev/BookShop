package com.lubogeorgiev.bookshop.services.book;

import com.lubogeorgiev.bookshop.domain.entities.Book;
import com.lubogeorgiev.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
