package com.example.softunispringjson.service;

import com.example.softunispringjson.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;
    Set<Category> findRandomCategorySet();
}
