package com.example.softunispringjson.service.impl;

import com.example.softunispringjson.model.dto.CategorySeedDto;
import com.example.softunispringjson.model.entity.Category;
import com.example.softunispringjson.repository.CategoryRepository;
import com.example.softunispringjson.service.CategoryService;
import com.example.softunispringjson.util.ValidationUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.softunispringjson.constanats.GlobalConstants.*;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private static final String CATEGORIES_FILE_NAME = "categories.json";

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME));

        CategorySeedDto[] categorySeedDtos = gson
                .fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategorySet() {
        Set<Category> categorySet = new HashSet<>();
        int categoryCount = ThreadLocalRandom
                .current().nextInt(1, 3 + 1);

        Long totalCategoriesCount = categoryRepository.count();

        for (int i = 0; i < categoryCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, totalCategoriesCount + 1);

            categorySet.add(categoryRepository.findById(randomId).orElse(null));
        }

        return categorySet;
    }
}
