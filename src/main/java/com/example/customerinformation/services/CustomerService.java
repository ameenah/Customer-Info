package com.example.customerinformation.services;

import com.example.customerinformation.models.Address;
import com.example.customerinformation.models.Customer;
import com.example.customerinformation.repositories.AddressRepo;
import com.example.customerinformation.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepo customerRepo ;

    @Autowired
    private final AddressRepo addressRepo ;

    public CustomerService(CustomerRepo customerRepo, AddressRepo addressRepo){

        this.customerRepo = customerRepo ;
        this.addressRepo = addressRepo ;
    }

    public List<Customer>  getCustomers( ){
        List<Customer>  customers = customerRepo.findAll();
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT );
        }
        return customers;
    }

    public Optional<Customer>   getCustomerById(Long customerId){
        Optional<Customer> customer =  customerRepo.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT );
        }
        return customer;
    }

    public void addNewCustomer(Customer customer){
        Customer customerResult = customerRepo.save(customer);

        for (Address add: customer.getAddress()) {
            add.setCustomer(customer);
            addressRepo.save(add);
        }
        Map<String, String> resultMap = new HashMap<>();
        if (customerResult == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    public void createAddressForCustomer(Long customerId, Address address){
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        address.setCustomer(customer.get());
        addressRepo.save(address);
    }
    public void deleteAddressForCustomer(Long customertId, Long addressId){

        Customer customer = customerRepo.getById(customertId);
        List <Address> addressesList = customer.getAddress();
        if (customer == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT );
        }
        addressRepo.deleteById(addressId);
    }
    public void deleteCustomer(Long customerID){
        boolean exists = customerRepo.existsById(customerID);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT );
        }
        customerRepo.deleteById(customerID);
    }

    public Set<Customer>  getCustomersByCity(String city) {
        Set<Customer> customers = customerRepo.findCustomersByCity(city);
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        return customers;

    }

    public Set<Customer> getCustomersByPhonePrefix(int phonePrefix) {
        String prefixSt = Integer.toString(phonePrefix);
        Set<Customer> customers = customerRepo.findCustomerByPhoneNumberContaining(prefixSt);
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        return customers;
    }
}
