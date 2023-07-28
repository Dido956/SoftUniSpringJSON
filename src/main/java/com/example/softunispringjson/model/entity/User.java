package com.softuni.XMLdemo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private Integer age;
    @ManyToMany
    private Set<User> friends;
    @OneToMany(mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Product> soldProducts;

}
