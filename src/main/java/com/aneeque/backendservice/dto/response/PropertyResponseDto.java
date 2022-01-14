package com.aneeque.backendservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDto {

    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    private String data;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

}
