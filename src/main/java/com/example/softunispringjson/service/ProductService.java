package com.example.softunispringjson.service;

import com.example.softunispringjson.model.dto.CategoryWithCountAndAverageAndTotalDto;
import com.example.softunispringjson.model.dto.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNameAndPriceDto> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal lower, BigDecimal upper);

    List<CategoryWithCountAndAverageAndTotalDto> findAllCategoryNamesWithTheirNumberOfProductsAveragePriceAndTotalRevenue();
}
