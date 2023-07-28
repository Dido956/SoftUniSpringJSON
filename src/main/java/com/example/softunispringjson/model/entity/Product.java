package com.softuni.XMLdemo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity{
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @ManyToOne
    private User seller;
    @ManyToOne
    private User buyer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_categories",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
}
