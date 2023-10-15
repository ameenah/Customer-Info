package com.example.customerinformation.customer;

import com.example.customerinformation.address.Address;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotNull(message = "First Name is missing ")
    @NotBlank(message = "First Name is missing")
    private String firstName ;
    @NotNull(message = "Last Name is missing ")
    @NotBlank(message = "Last Name is missing")
    private String lastName ;
    @Pattern(regexp = "^\\d{10}$",message = "Phone Number is invalid ")
    private String phoneNumber ;
    @Email(message = "Email is invalid")
    private String email ;


    @OneToMany(mappedBy = "customer" ,
            orphanRemoval = true)
    private List<Address> addresses ;

    public Customer(){

    }
//    public Customer(long id, String firstName, String lastName, String phoneNumber, String email, Set<Address> addresses) {
//
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.addresses = addresses;
//    }

    public Customer(String firstName, String lastName, String phoneNumber, String email, ArrayList<Address>  addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addresses = addresses;
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address>  getAddress() {
        return addresses;
    }

    public void setAddress(List<Address>  addresses) {
        this.addresses = addresses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + addresses +
                '}';
    }
}
