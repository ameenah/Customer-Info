package com.example.CustomerAPI.Address;

import com.example.CustomerAPI.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String type ;
    private String city;
    private String country ;
    private String addressLine ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer ;

    public Address(){}

    public Address(Long id, String type, String city, String country, String addressLine) {
        this.id = id;
        this.type = type;
        this.city = city;
        this.country = country;
        this.addressLine = addressLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", City='" + city + '\'' +
                ", country='" + country + '\'' +
                ", addressLine='" + addressLine + '\'' +
                '}';
    }
}
