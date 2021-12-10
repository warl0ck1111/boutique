package com.aneeque.backendservice.privilege;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class PrivilegeRequest {

    private String name;

    private String description;

    private String module;

}
