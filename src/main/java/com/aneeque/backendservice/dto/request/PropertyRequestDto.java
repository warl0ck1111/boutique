package com.aneeque.backendservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequestDto {

    private String description;

    @NotBlank(message = "data field cannot be empty ")
    @NotNull(message = "data field cannot be empty ")
    private String data;


}
