package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */


@Data
@NoArgsConstructor
public class TagRequestDto {

    private String name;

    private String slug;

    private String description;
}
