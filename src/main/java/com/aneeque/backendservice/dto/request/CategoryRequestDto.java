package com.aneeque.backendservice.dto.request;

import com.aneeque.backendservice.data.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {


    @JsonProperty("title")
    @NotNull(message = "name can not be empty")
    @NotBlank(message = "name can not be empty")
    private String name;

    private String description;

    @JsonProperty("children")
    private Set<Long> subCategoryIds = new HashSet<>();

    private Set<Long> attributesIds = new HashSet<>();



}
