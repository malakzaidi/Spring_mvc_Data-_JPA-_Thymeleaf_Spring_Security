package org.springmvc.hospital.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springmvc.hospital.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
