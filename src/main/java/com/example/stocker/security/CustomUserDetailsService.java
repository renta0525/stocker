package com.example.stocker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.User;
import com.example.stocker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername (String mail) throws UsernameNotFoundException {
        User user = repository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getMail())
                .password(user.getPassword())
                .roles(user.isAdminFlg() ? "ADMIN" : "USER")
                .build();
    }
}
