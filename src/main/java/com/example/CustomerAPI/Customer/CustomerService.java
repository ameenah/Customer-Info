package com.example.CustomerAPI.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepo customerRepo ;

    public CustomerService(CustomerRepo customerRepo){
        this.customerRepo = customerRepo ;
    }

    public List<Customer> getCusomers(){
        return customerRepo.findAll();
    }

    public void addNewCustomer(Customer customer){
        //TODO parameters Validation
        customerRepo.save(customer);
        System.out.println(customer.toString());
    }

    public void deleteCustomer(Long customerID){
        boolean exists = customerRepo.existsById(customerID);
        if(!exists){
            throw  new IllegalStateException("Customer not Exist");

        }
        customerRepo.deleteById(customerID);
    }

}
