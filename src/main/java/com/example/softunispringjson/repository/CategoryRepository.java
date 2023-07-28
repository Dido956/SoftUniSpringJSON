package com.example.softunispringjson.repository;

import com.example.softunispringjson.model.dto.CategoryWithCountAndAverageAndTotalDto;
import com.example.softunispringjson.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT NEW com.example.softunispringjson.model.dto.CategoryWithCountAndAverageAndTotalDto(c.name, COUNT(p.Id), AVG(p.price), SUM(p.price)) " +
            "FROM Category c " +
            "LEFT JOIN c.products p " +
            "GROUP BY c.Id " +
            "ORDER BY COUNT(p.Id) DESC")
    Optional<List<CategoryWithCountAndAverageAndTotalDto>> findAllCategoryNamesWithTheirNumberOfProductsAveragePriceAndTotalRevenue();

}
