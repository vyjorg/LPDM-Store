package com.lpdm.msstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpdm.msstore.model.location.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

@Entity
@Table(name = "store", schema = "public")
public class Store {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(name = "address_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int addressId;

    @Transient
    private Address address;

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressId=" + addressId +
                ", address=" + address +
                '}';
    }
}