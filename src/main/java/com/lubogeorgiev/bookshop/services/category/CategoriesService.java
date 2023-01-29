package com.lubogeorgiev.bookshop.services.category;

import com.lubogeorgiev.bookshop.domain.entities.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoriesService {

    void seedCategories(List<Category> categories);

    boolean isCategorySeeded();

    Set<Category> getRandomCategories();
}
