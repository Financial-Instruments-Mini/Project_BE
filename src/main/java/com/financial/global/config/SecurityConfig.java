package com.financial.global.config;

import com.financial.member.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    String[] matchers = {"/","/api/v1/auth/**","/api/v1/keyword/**", "/api/v1/products/**","/api/v1/products/"
            ,"/swagger-ui.html","/swagger/**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs"};

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(
                        matchers
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Collections.singletonList("*")); // ?????? Origin????????? ????????? ??????
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // ?????? Http Methods??? ???????????? ????????? ??????
        configuration.setAllowedHeaders(List.of("authorization", "content-type", "x-auth-token")); // ?????? ????????? ???????????? ????????? ??????
        configuration.setExposedHeaders(Collections.singletonList("x-auth-token")); // ????????? CSRF ????????? ?????? ????????? ?????? ?????? ?????? ????????? ??????
        configuration.setAllowCredentials(true); // ????????? ?????? ??????(??????, ?????????) ????????? ????????? ???

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // ?????? URL??? ?????? ?????? ????????? ????????? CORS ????????? ??? ???
        return source;
    }

}
