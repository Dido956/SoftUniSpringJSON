package com.example.softunispringjson.model.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductWithBuyerDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
    private String buyerLastName;

}
