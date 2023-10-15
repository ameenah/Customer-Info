package com.example.customerinformation.repositories;

import com.example.customerinformation.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
