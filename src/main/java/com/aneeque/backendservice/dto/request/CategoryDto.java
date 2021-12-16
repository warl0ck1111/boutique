package com.aneeque.backendservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotBlank(message = "name can not be empty")
    private String name;


    private List<PropertyDto> properties;

    private List<AttributeDto> attributes;


    private Long parentId;
    private Long lft;
    private Long rgt;

}
