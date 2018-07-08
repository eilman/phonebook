package com.demo.phonebook;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long contactId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userId")
    private User user;


    public Contact(){

    }

    public Contact(Long contactId, String firstName, String lastName) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
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

    @JsonIgnore //tekrar getirmeyi önlülyo
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
