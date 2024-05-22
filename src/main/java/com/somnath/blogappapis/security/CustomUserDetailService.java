package com.somnath.blogappapis.security;

import com.somnath.blogappapis.entities.User;
import com.somnath.blogappapis.exceptions.ResourceNotFoundException;
import com.somnath.blogappapis.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in CustomUserDetailService username: "+username);
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User ", " email: " + username, 0));

        return user;
    }
}
