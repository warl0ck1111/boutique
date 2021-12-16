package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "department name cannot be null or empty")
    private String name;
}
