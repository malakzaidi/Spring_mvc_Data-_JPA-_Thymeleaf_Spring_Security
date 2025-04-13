package org.springmvc.hospital.security.service;


import lombok.AllArgsConstructor;
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
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    @Override
    public AppUser addNewUser(String username , String email ,String confirmpassword , String password ){
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser != null){
            throw new RuntimeException("User already exists");
        }
        if (!password.equals(confirmpassword) ) {
            throw new RuntimeException("Passwords do not match");
        }
        appUser = AppUser.builder().userId(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(password)
                .build();
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;


    }


    @Override
    public AppRole addNewRole(String role) {
        return null;
    }

    @Override
    public void addRoleToAppUser(String role, String username) {

    }

    @Override
    public void deleteRoleToAppUser(String role, String username) {

    }
}
