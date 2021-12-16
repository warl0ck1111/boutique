package com.aneeque.backendservice.dto.request;

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
