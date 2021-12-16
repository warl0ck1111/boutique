package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class AttributeDto {

    @NotBlank(message = "attribute name field cannot be empty")
    private String name;

    @NotBlank(message = "attribute department field cannot be empty")
    private String department;

}
