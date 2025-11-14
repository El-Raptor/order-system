package com.raptor.ordersystem.service;

import com.raptor.ordersystem.entity.User;
import com.raptor.ordersystem.entity.UserPrincipal;
import com.raptor.ordersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElse(null);

        if (user == null)
            throw new UsernameNotFoundException("User not found.");

        return new UserPrincipal(user);
    }
}
