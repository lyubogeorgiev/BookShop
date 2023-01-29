package com.lubogeorgiev.bookshop.services.category;

import com.lubogeorgiev.bookshop.domain.entities.Category;

import java.util.List;

public interface CategoriesService {

    void seedCategories(List<Category> categories);
}
