package com.example.customerinformation.customer;

import com.example.customerinformation.address.Address;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService ;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService ;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return  customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomersById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);
    }
    @PostMapping
    public void createNewCustomer(@RequestBody Customer customer){
         customerService.addNewCustomer(customer);
    }

    @PostMapping("/{customerId}/address")
    public void createAddressForCustomer(@PathVariable Long customerId, @RequestBody Address address){
        customerService.createAddressForCustomer(customerId, address) ;
    }

    @DeleteMapping("/{customerId}/address/{addressId}")
    public void deleteAddressForCustomer(@PathVariable Long customerId,@PathVariable Long addressId){
        customerService.deleteAddressForCustomer(customerId, addressId);
    }

    @GetMapping("/city/{city}")
    public Set<Customer> getCustomersByCity(@PathVariable String city){
        return  customerService.getCustomersByCity(city);
    }

    @GetMapping("/phone/{phonePrefix}")
    public Set<Customer> getCustomersByPhonePrefix(@PathVariable int phonePrefix){
        return customerService.getCustomersByPhonePrefix(phonePrefix);
    }


}
