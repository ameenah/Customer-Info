package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import com.example.CustomerAPI.Address.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<Customer> getCustomers( ){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long customertId){

        return customerRepo.findById(customertId);
    }

    public void addNewCustomer(Customer customer){
        //TODO parameters Validation
        customerRepo.save(customer);
        System.out.println(customer.toString());
    }

    public void createAddressForCustomer(Long customertId, Address address){


        Optional<Customer> customer = customerRepo.findById(customertId);
        if (customer.isPresent() ){
            address.setCustomer(customer.get());
            addressRepo.save(address);
        }


    }
    public void deleteAddressForCustomer(Long customertId, Long addressId){

        boolean customerExists = customerRepo.existsById(customertId);
        boolean addressExists = addressRepo.existsById(addressId);

        if (!customerExists || !addressExists ){
            throw  new IllegalStateException("Customer not Exist");
        }
        addressRepo.deleteById(addressId);

    }
    public void deleteCustomer(Long customerID){
        boolean exists = customerRepo.existsById(customerID);
        if(!exists){
            throw  new IllegalStateException("Customer not Exist");

        }
        customerRepo.deleteById(customerID);
    }

    public Set<Customer> getCustomersByCity(String city) {
        return customerRepo.findCustomersByCity(city);

    }

    public Set<Customer> getCustomersByPhonePrefix(int phonePrefix) {
        String prefixSt = Integer.toString(phonePrefix);
        return customerRepo.findCustomerByPhoneNumberContaining(prefixSt);
    }
}
