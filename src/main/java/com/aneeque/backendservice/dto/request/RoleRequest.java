package com.aneeque.backendservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @NotBlank(message = "name field must not be empty")
    private String name;

    @NotBlank(message = "description field must not be empty")
    private String description;

    private String entity = "ANEEQUE";

    private boolean updatePrivileges;

    private List<Long> permissions = new ArrayList<>();



}
