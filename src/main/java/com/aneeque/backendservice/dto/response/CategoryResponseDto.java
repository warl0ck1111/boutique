package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
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
public class CategoryResponseDto {

    @JsonProperty("key")
    private Long id;

    @JsonProperty("title")
    @NotBlank(message = "name can not be empty")
    private String name;

    private String description;

    @JsonIgnore
    private CategoryType categoryType;

    @JsonProperty("categoryType")
    private String category;

    @JsonProperty("children")
    @ManyToMany
    private List<Category> subCategories = new ArrayList<>();

    @OneToMany
    private List<Attribute> attributes = new ArrayList<>();


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;


}
