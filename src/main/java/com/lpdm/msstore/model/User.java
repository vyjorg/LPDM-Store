package com.lpdm.msstore.model;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

public class User {

    private int id;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
