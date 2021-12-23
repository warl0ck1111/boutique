package com.aneeque.backendservice.dto.request;

import com.aneeque.backendservice.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank(message = "name can not be empty")
    private String name;

    private String description;

    private List<Long> levelIds;

    private Set<Category> subCategories;

    private List<PropertyDto> properties;

    private List<AttributeDto> attributes;

}
