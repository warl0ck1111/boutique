package com.aneeque.backendservice.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {

    @NotBlank
    private String description;

    @NotBlank
    private String data;


}
