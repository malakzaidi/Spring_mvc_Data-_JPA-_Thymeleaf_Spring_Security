package org.springmvc.hospital.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springmvc.hospital.security.service.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
@AllArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build(),
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsServiceImpl userDetailsServiceImpl) throws Exception {
        httpSecurity
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/notAuthorized").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/notAuthorized")
                )
                .rememberMe(Customizer.withDefaults());

        httpSecurity.userDetailsService(userDetailsServiceImpl);
        return httpSecurity.build();
    }
}