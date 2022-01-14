package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.data.entity.Property;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class AttributeResponseDto {

    private Long id;

    private String name;

    private Collection<Property> properties;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

}
