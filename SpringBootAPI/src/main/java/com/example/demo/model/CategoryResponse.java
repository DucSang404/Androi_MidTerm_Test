package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse { // phạm tiến anh - 22110282
    String id;
    String name;
    String description;
    String imageUrl;
}
