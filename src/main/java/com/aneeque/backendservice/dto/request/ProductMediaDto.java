package com.aneeque.backendservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMediaDto {

    private String fileName;

    private String fileType;

    @JsonIgnore
    private Long productId;

    public ProductMediaDto(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
