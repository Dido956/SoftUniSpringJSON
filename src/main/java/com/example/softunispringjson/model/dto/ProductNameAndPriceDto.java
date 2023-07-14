package com.example.softunispringjson.model.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductNameAndPriceDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String seller;
}
