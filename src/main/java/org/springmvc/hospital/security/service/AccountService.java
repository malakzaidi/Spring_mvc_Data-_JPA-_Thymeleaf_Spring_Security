package org.springmvc.hospital.security.service;

import org.springmvc.hospital.security.entities.AppRole;
import org.springmvc.hospital.security.entities.AppUser;

    public interface AccountService{
    AppUser addNewUser(String username,String password,String email,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToAppUser(String role, String username);
    void deleteRoleToAppUser(String role, String username);
}
