package org.springmvc.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springmvc.hospital.entities.Patient;
import org.springmvc.hospital.repositories.PatientRepository;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication  implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Patient patient = new Patient();
        patient.setNom("Malak");
        patient.setDateNaissance(new java.util.Date());
        patient.setMalade(true);
        patient.setScore(10);
        patientRepository.save(patient);
    }
    Patient patient2 = new Patient(null ,"Ahmed", new Date(),false,10);

    Patient patient3 = Patient.builder().nom("Ahmed").dateNaissance(new Date()).malade(true).score(10).build();

}
