package com.mycompany;

import java.io.Serializable;

/**
 * Public class that represents a single contact object with personal details.
 */
public class Contacts implements Serializable {

    // Enabling serialization of file loading/saving contacts.
    private static final long serialVersionUID = 1L;
    // private field attributes/properties of a single contact object
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String phone;
    private String email;
    private String notes;

    /**
     * Contructor that constructs a single new Contacts object with all its attribute/properties details.
     */
    public Contacts(String firstName, String lastName, String street, String city, String state, String phone, String email, String notes) {
        this.firstName = firstName;
        this.lastName = lastName; 
        this.street = street;
        this.city = city; 
        this.state = state;
        this.phone = phone; 
        this.email = email;
        this.notes = notes; 
    }

    /**
    * 
    * All getter and setter methods.
    */
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city; 
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone; 
    }

    public void setPhone(String phone) {
        this.phone = phone; 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    /**
    * This returns an entire string object representation of the single contact in the format below.
    * This method will be used by the 'ListView' to display the contacts in the list. 
    */
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
}
