package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class AttributeRequestDto {


    @NotBlank(message = "attribute name field cannot be empty")
    @NotNull(message = "attribute name field cannot be empty")
    private String name;

    Set<Long> propertyIds = new HashSet<>();


}
