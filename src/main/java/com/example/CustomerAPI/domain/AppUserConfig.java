package com.example.CustomerAPI.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class AppUserConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner commandLineRunnerAppUser (AppUserService appUserService){

        return  args -> {
          appUserService.saveRole(new Role(null, "ROLE_USER"));
          appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
          appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
          appUserService.saveRole(new Role(null, "ROLE_SUPERADMIN"));

          appUserService.saveUser(new AppUser(null , "Ameenah Khalid", "ameenah", "1234", new ArrayList<>() ));
          appUserService.saveUser(new AppUser(null , "Ameenah Khalid", "kalvad", "1234", new ArrayList<>() ));

          appUserService.addRoleToUser("ameenah", "ROLE_SUPERADMIN");


        };

    }
}
