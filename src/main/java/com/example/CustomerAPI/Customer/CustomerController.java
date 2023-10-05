package com.example.CustomerAPI.Customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService ;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService ;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        System.out.println("========= inside get");
        return customerService.getCusomers();
    }

    @PostMapping
    public void createNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }



}
