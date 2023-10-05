package com.example.CustomerAPI.Customer;

import javax.persistence.*;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String type ;
    private String City ;
    private String country ;
    private String addressLine ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer ;


    public Address(Long id, String type, String city, String country, String addressLine) {
        this.id = id;
        this.type = type;
        City = city;
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
        return City;
    }

    public void setCity(String city) {
        City = city;
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
                ", City='" + City + '\'' +
                ", country='" + country + '\'' +
                ", addressLine='" + addressLine + '\'' +
                '}';
    }
}
