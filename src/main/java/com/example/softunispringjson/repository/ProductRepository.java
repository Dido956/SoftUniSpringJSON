package com.example.softunispringjson.repository;

import com.example.softunispringjson.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal lower, BigDecimal upper);
}
