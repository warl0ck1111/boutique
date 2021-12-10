package com.aneeque.backendservice.role;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class PrivilegeListRequest {
    private List<Long> privileges;
}
