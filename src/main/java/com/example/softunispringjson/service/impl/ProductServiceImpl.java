package com.example.softunispringjson.service.impl;

import com.example.softunispringjson.model.dto.CategoryWithCountAndAverageAndTotalDto;
import com.example.softunispringjson.model.dto.ProductNameAndPriceDto;
import com.example.softunispringjson.model.dto.ProductSeedDto;
import com.example.softunispringjson.model.entity.Product;
import com.example.softunispringjson.repository.ProductRepository;
import com.example.softunispringjson.service.CategoryService;
import com.example.softunispringjson.service.ProductService;
import com.example.softunispringjson.service.UserService;
import com.example.softunispringjson.util.ValidationUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.softunispringjson.constanats.GlobalConstants.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_FILE_NAME = "products.json";

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME));

        ProductSeedDto[] productSeedDtos = gson
                .fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());

                    if (productSeedDto.getPrice().compareTo(BigDecimal.valueOf(900)) > 0) {
                        product.setBuyer(userService.findRandomUser());
                    }
                    product.setCategories(categoryService.findRandomCategorySet());

                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public List<ProductNameAndPriceDto> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal lower, BigDecimal upper) {
        return productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(lower, upper)
                .stream()
                .map(product -> {
                    ProductNameAndPriceDto productNameAndPriceDto = modelMapper.map(product, ProductNameAndPriceDto.class);

                    productNameAndPriceDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getFirstName()));

                    return productNameAndPriceDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<CategoryWithCountAndAverageAndTotalDto> findAllCategoryNamesWithTheirNumberOfProductsAveragePriceAndTotalRevenue() {

        return null;
    }
}
