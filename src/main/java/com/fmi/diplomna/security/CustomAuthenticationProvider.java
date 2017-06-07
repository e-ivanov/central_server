/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.security;

import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.services.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        String username = auth.getName();
        User user = userService.loadByUserName(username);
        
        
        if(user == null){
            throw new BadCredentialsException("Невалидно потребителско име и/или парола!");
        }
        
        String password = (String)auth.getCredentials();
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Невалидно потребителско име и/или парола!");
        }
        
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(user, password, roles);
        
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
