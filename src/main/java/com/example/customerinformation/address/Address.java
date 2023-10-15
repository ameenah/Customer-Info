package com.example.customerinformation.address;

import com.example.customerinformation.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotNull(message = "Address type is missing ")
    @NotBlank(message = "Address type is missing")
    private String type ;
    @NotNull(message = "city is missing ")
    @NotBlank(message = "city is missing")
    private String city;
    @NotNull(message = "country is missing ")
    @NotBlank(message = "country is missing")
    private String country ;
    @NotNull(message = "Address Line is missing ")
    @NotBlank(message = "Address Line is missing")
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

    public Address(String type, String city, String country, String addressLine) {
        this.type = type;
        this.city = city;
        this.country = country;
        this.addressLine = addressLine;
    }

    public Address( String type, String city, String country, String addressLine, Customer customer) {
        this.type = type;
        this.city = city;
        this.country = country;
        this.addressLine = addressLine;
        this.customer = customer;
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
