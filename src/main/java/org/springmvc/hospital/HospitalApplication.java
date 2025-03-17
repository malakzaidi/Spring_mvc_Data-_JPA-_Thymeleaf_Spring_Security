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
        patientRepository.save(new Patient(null ,"Malak", new Date(),false,32));
        patientRepository.save(new Patient(null ,"Samia", new Date(),true,35));
        patientRepository.save(new Patient(null ,"Douaa", new Date(),false,132));
        patientRepository.save(new Patient(null ,"Nada", new Date(),false,62));
    }


}
