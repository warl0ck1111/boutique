package com.aneeque.backendservice.token;

import com.aneeque.backendservice.appuser.AppUser;
import com.aneeque.backendservice.appuser.AppUserRepository;
import com.aneeque.backendservice.appuser.AppUserService;
import com.aneeque.backendservice.appuser.AuthenticationResponse;
import com.aneeque.backendservice.exception.ApiRequestException;
import com.aneeque.backendservice.security.jwt.JwtUtil;
import com.aneeque.backendservice.util.validator.EmailValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;


/**
 * @author Okala III
 */
@Slf4j
@Data
@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository appUserRepository;


    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token setConfirmedAt(String token) {
        Token tokenValidation = tokenRepository.findByToken(token).orElseThrow(() -> new ApiRequestException("invalid token", HttpStatus.BAD_REQUEST));
        tokenValidation.setConfirmedAt(LocalDateTime.now());
        return tokenRepository.save(tokenValidation);
    }

    @Transactional
    public AuthenticationResponse confirmToken(String token) {
        if (token.length() != 6) throw new ApiRequestException("invalid token", HttpStatus.BAD_REQUEST);
        Token tokenValidation = getToken(token).orElseThrow(() -> {
            return new ApiRequestException("opt not found", HttpStatus.NOT_FOUND);
        });

        if (tokenValidation.getConfirmedAt() != null)
            throw new ApiRequestException("token already used", HttpStatus.BAD_REQUEST);

        LocalDateTime expiredAt = tokenValidation.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new ApiRequestException("token expired", HttpStatus.BAD_REQUEST);

        Token confirmedToken = setConfirmedAt(token);
        String response = appUserService.enableAppUserByEmail(confirmedToken.getEmailAddress());
        log.info(response);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(confirmedToken.getEmailAddress());
        AppUser appUser = appUserRepository.findByEmailAddress(confirmedToken.getEmailAddress()).get();
//
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, appUser.getRole().getName(), appUser.getAllUserPrivileges(), appUser.getId().toString(), appUser.getFirstName(), appUser.getLastName(), appUser.getLastLogin());
//        return "Token confirmed successfully";
    }

    public String generateToken(int tokenLength) {
        UUID uuid = UUID.randomUUID();
        String Token = uuid.toString().substring(0, tokenLength).toUpperCase(Locale.ROOT);
//        log.debug("token is:"+ token);
        return Token;
    }

}
