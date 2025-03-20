package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tbl_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity extends AbstractEntity { // phạm tiến anh - 22110282
    String name;
    String description;
    String imageUrl;

    @ManyToMany(mappedBy = "categorys")
    private Set<UserEntity> users = new HashSet<>();
}
