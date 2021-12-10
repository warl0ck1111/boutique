package com.aneeque.backendservice.token;

import com.aneeque.backendservice.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Okala III
 */

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    boolean existsByToken(String Token);

    Optional<Token> findByEmailAddressAndTokenType(String email, TokenType tokenType);


}
