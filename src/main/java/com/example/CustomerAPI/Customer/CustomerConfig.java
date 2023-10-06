package com.example.CustomerAPI.Customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner cmdRunner (CustomerRepo repo){
        return args -> {
          Customer a = new Customer(
                  "ameenah",
                  "mohamed",
                  "0990000000",

                  "ameenah.k.a.m@gmail.com"

          );
            Customer b = new Customer(
                    "ameenah",
                    "mohamed",
                    "0990000000",

                    "ameenah.k.a.m@gmail.com"

            );
            Customer c = new Customer(
                    "ameenah",
                    "mohamed",
                    "0900000000",

                    "ameenah.k.a.m@gmail.com"

            );
            repo.saveAll(
                    List.of(a, b, c )
            );
        };
    }
}
