package com.example.CustomerAPI.Customer;

import com.example.CustomerAPI.Address.Address;
import com.example.CustomerAPI.Address.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public HashMap<String, Object>  getCustomers( ){
        List<Customer>  customers = customerRepo.findAll();
        HashMap<String, Object> resultMap = new HashMap<>();
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customers Not Found");
        }
        resultMap.put("result", "success");
        resultMap.put("customers", customers);

        return resultMap;
    }

    public HashMap<String, Object>   getCustomerById(Long customerId){
        Optional<Customer> customer =  customerRepo.findById(customerId);
        HashMap<String, Object> resultMap = new HashMap<>();
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        resultMap.put("result", "success");
        resultMap.put("customer", customer.get());

        return resultMap;
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

        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }

        address.setCustomer(customer.get());
        addressRepo.save(address);
        resultMap.put("result", "success");
        return  resultMap ;

    }
    public ResponseEntity<?> deleteAddressForCustomer(Long customertId, Long addressId){

        boolean customerExists = customerRepo.existsById(customertId);
        boolean addressExists = addressRepo.existsById(addressId);

        Map<String, String> resultMap = new HashMap<>();
        addressRepo.deleteById(addressId);
        resultMap.put("result", "success");
        return new ResponseEntity<>(resultMap,HttpStatus.OK);

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

    public HashMap<String, Object>  getCustomersByCity(String city) {
        Set<Customer> customers = customerRepo.findCustomersByCity(city);
        HashMap<String, Object> resultMap = new HashMap<>();
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        resultMap.put("result", "success");
        resultMap.put("customers", customers);

        return resultMap;

    }

    public HashMap<String, Object>  getCustomersByPhonePrefix(int phonePrefix) {
        HashMap<String, Object> resultMap = new HashMap<>();
        String prefixSt = Integer.toString(phonePrefix);
        Set<Customer> customers = customerRepo.findCustomerByPhoneNumberContaining(prefixSt);
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Customer Not Found");
        }
        resultMap.put("result", "success");
        resultMap.put("customers", customers);
        return resultMap;
    }
}
