package com.example.demo.model;

// Nguyễn Công Quý - 22110403

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

// Cho biết khi response trả về thì field nào bị null sẽ không cần đưa ra
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    int code;
    String message;
    T result;
}
