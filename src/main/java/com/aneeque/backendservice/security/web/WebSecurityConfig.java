package com.aneeque.backendservice.security.web;

import com.aneeque.backendservice.security.jwt.filter.JwtRequestFilter;
import com.aneeque.backendservice.security.web.filters.SimpleAccessDeniedHandler;
import com.aneeque.backendservice.security.web.filters.SimpleAuthenticationEntryPoint;
import com.aneeque.backendservice.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author B.O Okala III
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private SimpleAccessDeniedHandler simpleAccessDeniedHandler;
    @Autowired
    private SimpleAuthenticationEntryPoint simpleAuthenticationEntryPoint;


    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/resources/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/user/auth/create",
            "/api/v1/user/auth/login",
            "/api/v1/user/auth/login/**",
            "/api/v1/user/auth/confirm-otp/**",
            "/api/v1/user/auth/auth/**/activate-account",
            "/api/v1/user/auth/**/forgot-pwd",
            "/api/v1/user/auth/reset-pwd",
            // other public endpoints of your API may be appended to this array
    };


    @Override
    protected void configure(HttpSecurity web) throws Exception {
        web
                .cors().and()
                .csrf().disable()
                .authorizeRequests()


                .antMatchers(
                        "**")
                .permitAll().anyRequest().authenticated()
                .and()
                //.exceptionHandling().accessDeniedHandler(simpleAccessDeniedHandler).authenticationEntryPoint(simpleAuthenticationEntryPoint);

                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        web.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        web.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        web.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.addAllowedOrigin("*");
                config.setAllowCredentials(true);
                return config;
            }
        });


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {
                if(HttpMethod.OPTIONS.matches(request.getMethod())){
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                }else
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        };
    }

}
