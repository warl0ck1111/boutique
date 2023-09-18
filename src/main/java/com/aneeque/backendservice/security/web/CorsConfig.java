package com.aneeque.backendservice.security.web;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author B.O Okala III
 */

//@Configuration
//@Component
public class CorsConfig implements WebMvcConfigurer {
//    @Value("${user-mgnt.trusted-origins}")
//    private String[] whitelist;
//
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
//                "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setExposedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new
//                UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return new CorsFilter(source);
//    }
}
