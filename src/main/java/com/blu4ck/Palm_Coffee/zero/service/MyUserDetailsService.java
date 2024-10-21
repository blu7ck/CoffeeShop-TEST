package com.blu4ck.Palm_Coffee.zero.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN")
                    .build();
        } else if ("cashier".equals(username)) {
            return User.withUsername("cashier")
                    .password(passwordEncoder().encode("cashier123"))
                    .roles("CASHIER")
                    .build();
        } else if ("guest".equals(username)) {
            return User.withUsername("guest")
                    .password(passwordEncoder().encode("guest123"))
                    .roles("GUEST")
                    .build();
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
        }
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
