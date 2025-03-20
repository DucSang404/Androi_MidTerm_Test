package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCategoryRequest { // phạm tiến anh - 22110282
    private String username;
    private String categoryId;
}
