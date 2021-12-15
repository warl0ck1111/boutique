package com.aneeque.backendservice.category.department;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Okala III
 */
@Data
@NoArgsConstructor
public class DepartmentResponseDto {

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
