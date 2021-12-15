package com.aneeque.backendservice.util;

import org.springframework.security.core.Authentication;

/**
 * @author Okala Bashir .O.
 */

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
