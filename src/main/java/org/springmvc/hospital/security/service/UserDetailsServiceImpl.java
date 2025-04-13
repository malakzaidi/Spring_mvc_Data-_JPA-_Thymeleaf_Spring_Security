package org.springmvc.hospital.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springmvc.hospital.security.entities.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        String[] roles = appUser.getRoles().stream()
                .map(u -> u.getRole())
                .toArray(String[]::new);

        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}






