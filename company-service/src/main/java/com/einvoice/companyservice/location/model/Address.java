package com.einvoice.companyservice.location.model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cityId")
    private City city;

    public Address() {
    }

    public Address(String address, City city) {
        this.address = address;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
