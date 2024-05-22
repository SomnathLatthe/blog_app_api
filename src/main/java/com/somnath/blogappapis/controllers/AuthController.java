package com.somnath.blogappapis.controllers;

import com.somnath.blogappapis.exceptions.ApiException;
import com.somnath.blogappapis.payloads.JwtAuthRequest;
import com.somnath.blogappapis.payloads.JwtAuthResponse;
import com.somnath.blogappapis.payloads.UserDto;
import com.somnath.blogappapis.security.CustomUserDetailService;
import com.somnath.blogappapis.security.JwtTokenHelper;
import com.somnath.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception
    {
        System.out.println("jwtAuthRequest.getUserName(): "+jwtAuthRequest.getUserName());
        this.authenticate(jwtAuthRequest.getUserName(),jwtAuthRequest.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        String token=this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) throws Exception{
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e)
        {
            System.out.println("Invalid details !!");
            throw new ApiException("Invalid username or password !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto registeredUser=this.userService.registerNewUser(userDto);

        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }
}
