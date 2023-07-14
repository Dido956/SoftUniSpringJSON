package com.example.softunispringjson.model.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSeedDto {
    @Expose
    private String firstName;
    @Expose
    @Size(min = 3)
    private String lastName;
    @Expose
    private Integer age;
}
