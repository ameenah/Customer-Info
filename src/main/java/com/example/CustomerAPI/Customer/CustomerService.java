package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import com.example.CustomerAPI.Address.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public List<Customer> getCustomers( ){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId){

        return customerRepo.findById(customerId);
    }

    public Map<String, String> addNewCustomer(Customer customer){
        Customer result = customerRepo.save(customer);
        Map<String, String> resultMap = new HashMap<>();
        if (result != null){
            resultMap.put("result", "success");
            return  resultMap ;
        }
        resultMap.put("result", "Operation Failed");
        return resultMap ;
    }

    public Map<String, String> createAddressForCustomer(Long customerId, Address address){
        Optional<Customer> customer = customerRepo.findById(customerId);
        Map<String, String> resultMap = new HashMap<>();
        if (customer.isPresent() ){
            address.setCustomer(customer.get());
            addressRepo.save(address);
            resultMap.put("result", "success");
            return  resultMap ;

        }
        resultMap.put("result", "Operation Failed");
        return resultMap ;

    }
    public Map<String, String> deleteAddressForCustomer(Long customertId, Long addressId){

        boolean customerExists = customerRepo.existsById(customertId);
        boolean addressExists = addressRepo.existsById(addressId);

        Map<String, String> resultMap = new HashMap<>();

        if (!customerExists || !addressExists ){
            resultMap.put("result", "Operation Failed");
            resultMap.put("message", "Customer or Address isn't found");
            return resultMap ;
        }
        addressRepo.deleteById(addressId);
        resultMap.put("result", "success");
        return resultMap ;

    }
    public Map<String, String> deleteCustomer(Long customerID){
        boolean exists = customerRepo.existsById(customerID);
        Map<String, String> resultMap = new HashMap<>();
        if(!exists){
            resultMap.put("message", "Customer found");
            return resultMap ;

        }
        customerRepo.deleteById(customerID);
        resultMap.put("result", "success");
        return resultMap ;

    }

    public Set<Customer> getCustomersByCity(String city) {
        return customerRepo.findCustomersByCity(city);

    }

    public Set<Customer> getCustomersByPhonePrefix(int phonePrefix) {
        String prefixSt = Integer.toString(phonePrefix);
        return customerRepo.findCustomerByPhoneNumberContaining(prefixSt);
    }
}
