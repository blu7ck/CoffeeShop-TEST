package com.blu4ck.Palm_Coffee.zero.config;

import com.blu4ck.Palm_Coffee.zero.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService myUserDetailsService;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, UserDetailsService myUserDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF'yi devre dışı bırakıyoruz
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate", "/vote/**", "/feedback/**").permitAll() // Guest/Anonim işlemler
                        .requestMatchers("/cash/**").hasRole("CASHIER") // Sadece Cashier
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Sadece Admin
                        .anyRequest().authenticated() // Diğer istekler için doğrulama zorunlu
                )
                .securityContext(securityContext -> securityContext
                        .requireExplicitSave(false) // Stateless yapı, session yok
                );

        // JWT filtreleme ekleniyor
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
