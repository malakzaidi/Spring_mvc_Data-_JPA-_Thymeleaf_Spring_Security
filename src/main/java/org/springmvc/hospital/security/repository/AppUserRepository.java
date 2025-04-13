package org.springmvc.hospital.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springmvc.hospital.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
