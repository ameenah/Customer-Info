package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomersById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);
    }
    @PostMapping
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer){
        Map<String, String> resultMap = customerService.addNewCustomer(customer);
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }

    @PostMapping("/{customerId}/address")
    public ResponseEntity<?> createAddressForCustomer(@PathVariable Long customerId, @RequestBody Address address){
        Map<String, String> resultMap  = customerService.createAddressForCustomer(customerId, address) ;
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/address/{addressId}")
    public ResponseEntity<?> deleteAddressForCustomer(@PathVariable Long customerId,@PathVariable Long addressId){
        Map<String, String> resultMap = customerService.deleteAddressForCustomer(customerId, addressId);
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public Set<Customer> getCustomersByCity(@PathVariable String city){
        return customerService.getCustomersByCity(city);
    }

    @GetMapping("/phone/{phonePrefix}")
    public Set<Customer> getCustomersByPhonePrefix(@PathVariable int phonePrefix){
        return customerService.getCustomersByPhonePrefix(phonePrefix);
    }


}
