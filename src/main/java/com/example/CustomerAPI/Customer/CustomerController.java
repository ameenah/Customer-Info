package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/{customertId}")
    public Optional<Customer> getCustomersById(@PathVariable Long customertId){
        return customerService.getCustomerById(customertId);
    }
    @PostMapping
    public void createNewCustomer(@RequestBody Customer customer){

        customerService.addNewCustomer(customer);
    }

    @PostMapping("/{customertId}/address")
    public void createAddressForCustomer(@PathVariable Long customertId, @RequestBody Address address){

        customerService.createAddressForCustomer(customertId, address);
    }

    @DeleteMapping("/{customertId}/address/{addressId}")
    public void deleteAddressForCustomer(@PathVariable Long customertId,@PathVariable Long addressId){
        customerService.deleteAddressForCustomer(customertId, addressId);
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
