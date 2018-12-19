package com.lpdm.msstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private int addressId;

    @Transient
    @JsonIgnore
    private Location location;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}