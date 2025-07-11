package com.tcs.product.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
////                    .requestMatchers("/user/authenticate","/user/register","/user/authenticate/forgot").permitAll()
////                    .requestMatchers("/user","/update","/lgt").hasRole("USER")
////                    .requestMatchers("/users").hasRole("ADMIN")
//            		.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                    .anyRequest().authenticated())
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    

//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(
//                    "/swagger-ui/**",
//                    "/swagger-ui.html",
//                    "/v3/api-docs/**",
//                    "/actuator/**"
//                ).permitAll()
//                .anyRequest().authenticated();
//
//            return http.build();
//        }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/actuator/**",
                "/product/products/**"          // ✅ Public GETs
            ).permitAll()
            .requestMatchers("/product/admin/**").authenticated() // ✅ Protected admin APIs
            .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
