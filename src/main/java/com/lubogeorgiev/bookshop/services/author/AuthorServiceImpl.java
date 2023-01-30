package com.lubogeorgiev.bookshop.services.author;

import com.lubogeorgiev.bookshop.domain.entities.Author;
import com.lubogeorgiev.bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        int count = (int) this.authorRepository.count();
        if (count != 0){
            Random rand = new Random();
            Long id = (long) rand.nextInt(1, (count));
            return this.authorRepository.findAuthorById(id).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }

    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate date) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllOrderByBooks() {
        return this.authorRepository.findAllOrderByBooks().orElseThrow(NoSuchElementException::new);
    }
}
