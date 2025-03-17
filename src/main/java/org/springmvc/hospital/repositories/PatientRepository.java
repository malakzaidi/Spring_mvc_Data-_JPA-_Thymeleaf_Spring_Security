package org.springmvc.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springmvc.hospital.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
