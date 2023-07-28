package com.example.softunispringjson.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CategoryWithCountAndAverageAndTotalDto {
    private String category;
    private Long numProducts;
    private Double avgPrice;
    private BigDecimal totalRevenue;

    public CategoryWithCountAndAverageAndTotalDto(String category, Long numProducts, Double avgPrice, BigDecimal totalRevenue) {
        this.category = category;
        this.numProducts = numProducts;
        this.avgPrice = avgPrice;
        this.totalRevenue = totalRevenue;
    }
}
