package org.springmvc.hospital.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springmvc.hospital.security.entities.AppRole;
import org.springmvc.hospital.security.entities.AppUser;
import org.springmvc.hospital.security.repository.AppRoleRepository;
import org.springmvc.hospital.security.repository.AppUserRepository;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private PasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    @Override
    public AppUser addNewUser(String username, String email, String password, String confirmpassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) {
            throw new RuntimeException("User already exists");
        }
        if (!password.equals(confirmpassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole != null) {
            throw new RuntimeException("Role already exists");
        }
        appRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToAppUser(String role, String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new RuntimeException("User not found: " + username);
        }

        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) {
            throw new RuntimeException("Role not found: " + role);
        }

        appUser.getRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteRoleToAppUser(String role, String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new RuntimeException("User not found: " + username);
        }

        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) {
            throw new RuntimeException("Role not found: " + role);
        }

        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
