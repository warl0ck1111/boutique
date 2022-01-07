package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class TagResponseDto {

    private Long id;

    private String name;

    private String slug;

    private String description;
}
