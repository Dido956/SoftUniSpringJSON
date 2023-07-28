package com.example.softunispringjson;

import com.example.softunispringjson.model.dto.CategoryWithCountAndAverageAndTotalDto;
import com.example.softunispringjson.model.dto.ProductNameAndPriceDto;
import com.example.softunispringjson.model.dto.UserSoldDto;
import com.example.softunispringjson.service.CategoryService;
import com.example.softunispringjson.service.ProductService;
import com.example.softunispringjson.service.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String OUTPUT_FOLDER_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.json";
    private static final String USERS_AND_SOLD_PRODUCTS = "users-and-sold-products.json";
    private static final String CATEGORIES_WITH_COUNT_AVG_SUM = "categories-count-avg-sum.json";
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter exercise:");
        int exNum = Integer.parseInt(bufferedReader.readLine());
        switch (exNum) {
            case 1 -> productsInRange();
            case 2 -> soldProducts();
            case 3 -> productCount();
        }

    }

    private void productCount() throws IOException {
        List<CategoryWithCountAndAverageAndTotalDto> categoryDtos = categoryService
                .findAllCategoryNamesWithTheirNumberOfProductsAveragePriceAndTotalRevenue();

        String content = gson.toJson(categoryDtos);

        writeToFile(OUTPUT_FOLDER_PATH + CATEGORIES_WITH_COUNT_AVG_SUM, content);
    }

    private void soldProducts() throws IOException {
        List<UserSoldDto> userSoldDtos = userService
                .findAllUserWithMoreThanOneSoldProduct();

        String content = gson.toJson(userSoldDtos);

        writeToFile(OUTPUT_FOLDER_PATH + USERS_AND_SOLD_PRODUCTS, content);
    }

    private void productsInRange() throws IOException {
        List<ProductNameAndPriceDto> productDtos = productService
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        String content = gson.toJson(productDtos);

        writeToFile(OUTPUT_FOLDER_PATH + PRODUCTS_IN_RANGE_FILE_NAME, content);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
