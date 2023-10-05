package com.example.CustomerAPI.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query( "SELECT c FROM Customer c JOIN c.addresses a WHERE a.city = :city")
    Set<Customer> findCustomersByCity(String city);

//    @Query( "SELECT c FROM Customer c  a WHERE c.phoneNumber like :phonePrefix")
//    Set<Customer> getCustomersByPhonePrefix(String phonePrefix);

    Set<Customer> findCustomerByPhoneNumberContaining(String phonePrefix);
}
