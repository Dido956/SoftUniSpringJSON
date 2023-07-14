package com.example.softunispringjson.model.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class CategorySeedDto {
    @Expose
    @Size(min = 3, max = 15)
    private String name;
}
