package com.aneeque.backendservice.security.jwt.filter;

import com.aneeque.backendservice.dto.response.ResponseDTO;
import com.aneeque.backendservice.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Okala III
 */

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);

        String username = null;
        String jwt = null;
            try {
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    jwt = authorizationHeader.substring("Bearer ".length());
                    username = jwtUtil.extractUsername(jwt);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                        if (jwtUtil.validateToken(jwt, userDetails)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                }
                filterChain.doFilter(request, response);
            }
            catch (MaxUploadSizeExceededException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
                SecurityContextHolder.clearContext();
                logger.error("Internal authentication service exception", internalAuthenticationServiceException);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AuthenticationException authenticationException) {
                SecurityContextHolder.clearContext();
                ResponseDTO rsp = new ResponseDTO("1008", authenticationException.getMessage());
                String tokenJsonResponse = new ObjectMapper().writeValueAsString(rsp);
                response.addHeader("Content-Type", "application/json");
                response.getWriter().print(tokenJsonResponse);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                // authenticationException.getMessage());
            }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex){
                SecurityContextHolder.clearContext();
                ResponseDTO rsp = new ResponseDTO("1008", ex.getMessage());
                String tokenJsonResponse = new ObjectMapper().writeValueAsString(rsp);
                response.addHeader("Content-Type", "application/json");
                response.getWriter().print(tokenJsonResponse);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }



    }


}
