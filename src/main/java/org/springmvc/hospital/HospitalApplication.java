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
import org.springmvc.hospital.security.service.AccountService;

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

    private boolean userExists(JdbcUserDetailsManager userDetailsManager ,String username) {
        try {
            userDetailsManager.loadUserByUsername(username);
            return true;
        } catch (Exception e ){
            return false;
        }

    }
    @Bean
    CommandLineRunner userSeeder (JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userExists(jdbcUserDetailsManager, "user11")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            if(!userExists(jdbcUserDetailsManager, "user22")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build());

            }
            if(!userExists(jdbcUserDetailsManager, "admin3")) {
                jdbcUserDetailsManager.createUser(User.withUsername("admin3").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build());

            }
        };
    };
    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");

            accountService.addRoleToAppUser("ADMIN","admin");
            accountService.addRoleToAppUser("USER","admin");
            accountService.addRoleToAppUser("USER","user1");
            accountService.addRoleToAppUser("USER","user2");

        };
    }
}

