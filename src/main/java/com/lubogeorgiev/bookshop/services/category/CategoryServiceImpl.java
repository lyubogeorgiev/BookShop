package com.lubogeorgiev.bookshop.services.category;

import com.lubogeorgiev.bookshop.domain.entities.Category;
import com.lubogeorgiev.bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoriesService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public boolean isCategorySeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public Set<Category> getRandomCategories() {
        final long count = this.categoryRepository.count();

        if (count != 0){
            final long randomCategoryId = new Random().nextLong(1L, count);
            return Set.of(this.categoryRepository.findById(randomCategoryId).orElseThrow(NoSuchElementException::new));
        }

        throw new NoSuchElementException();
    }
}
