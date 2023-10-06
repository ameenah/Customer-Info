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
    public ResponseEntity<?>  getCustomers(){
        Map<String, Object> resultMap = customerService.getCustomers();
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomersById(@PathVariable Long customerId){
        Map<String, Object> resultMap = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
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
        return customerService.deleteAddressForCustomer(customerId, addressId);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getCustomersByCity(@PathVariable String city){
        Map<String, Object> resultMap = customerService.getCustomersByCity(city);
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }

    @GetMapping("/phone/{phonePrefix}")
    public ResponseEntity<?> getCustomersByPhonePrefix(@PathVariable int phonePrefix){
        Map<String, Object> resultMap = customerService.getCustomersByPhonePrefix(phonePrefix);
        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }


}
