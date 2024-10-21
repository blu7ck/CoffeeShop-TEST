package com.blu4ck.Palm_Coffee.zero.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kullanıcıların kimlik doğrulama detayları
        if ("admin".equals(username)) {
            return new User("admin", new BCryptPasswordEncoder().encode("admin123"), new ArrayList<>());
        } else if ("cashier".equals(username)) {
            return new User("cashier", new BCryptPasswordEncoder().encode("cashier123"), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }
    }
}
