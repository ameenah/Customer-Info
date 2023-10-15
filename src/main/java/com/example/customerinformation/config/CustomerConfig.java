package com.example.customerinformation.config;

import com.example.customerinformation.models.Address;
import com.example.customerinformation.models.Customer;
import com.example.customerinformation.repositories.AddressRepo;
import com.example.customerinformation.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
          a.setAddress(List.of(address));

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
