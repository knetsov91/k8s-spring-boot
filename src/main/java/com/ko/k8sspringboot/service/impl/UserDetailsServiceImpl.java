package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.entity.UserEntity;
import com.ko.k8sspringboot.repository.UserRepository;
import com.ko.k8sspringboot.security.AuthenticationDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        AuthenticationDetails userDetail = AuthenticationDetails.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getUserRole())
                .build();
        return userDetail;
    }
}
