package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest { //// phạm tiến anh - 22110282
    String name;
    String description;
    String imageUrl;
}
