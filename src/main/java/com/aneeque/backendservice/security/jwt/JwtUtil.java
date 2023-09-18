package com.aneeque.backendservice.security.jwt;

import com.aneeque.backendservice.data.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Okala III
 */

@Slf4j
@Service
public class JwtUtil {

    @Value("${user-mgnt.jwt.lifetime.hours}")
    private int H;

    @Value("${user-mgnt.jwt.lifetime.minutes}")
    private int M;

    @Value("${user-mgnt.jwt.lifetime.seconds}")
    private int S;

    @Value("${user-mgnt.jwt.lifetime.milli-seconds}")
    private int MS;

    @Value("${user-mgnt.jwt.secret-key}")
    private String SECRET_KEY;


    public String extractUsername(String token) {
        String subject = (Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        return subject;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().toString());
        return createToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails, Authentication authentication) {
        AppUser appUser = (AppUser) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", appUser.getId());
//        claims.put("role", appUser.getRole().getName());
//        claims.put("privileges", appUser.getAllUserPrivileges());
        return createToken(claims, userDetails.getUsername());
    }


    private String createToken(Map<String, Object> claims, String subject) {
        final long JWT_LIFETIME_IN_MILLI_SECONDS = ((long) H *60*60*1000) + ((long) M *60*1000) + (S* 1000L) + MS;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() +  JWT_LIFETIME_IN_MILLI_SECONDS)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}