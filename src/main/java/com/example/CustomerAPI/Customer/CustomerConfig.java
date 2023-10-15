package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import com.example.CustomerAPI.Address.AddressRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner cmdRunner (CustomerRepo customerRepo , AddressRepo addressRepo){
        return args -> {
          Customer a = new Customer(
                  "ameenah",
                  "mohamed",
                  "0990000000",

                  "ameenah.k.a.m@gmail.com"

          );
          Address address =  new Address(
                  "home",
                  "Dubai",
                  "UAE",
                  "223th st",
                  a
          );
          a.setAddress(Set.of(address));

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
            customerRepo.saveAll(
                    List.of(a, b, c )
            );
            addressRepo.save(address);
        };
    }
}
