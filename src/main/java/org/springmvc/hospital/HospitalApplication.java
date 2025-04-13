package org.springmvc.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springmvc.hospital.entities.Patient;
import org.springmvc.hospital.repositories.PatientRepository;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication  {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    //@Bean
    CommandLineRunner patientSeeder (PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null ,"Malak", new Date(),false,132));
            patientRepository.save(new Patient(null ,"Samia", new Date(),true,135));
            patientRepository.save(new Patient(null ,"Douaa", new Date(),false,132));
            patientRepository.save(new Patient(null ,"Nada", new Date(),false,162));

        };

    }
    @Bean
    CommandLineRunner userSeeder (JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {
            jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build());
        };
    };
}

