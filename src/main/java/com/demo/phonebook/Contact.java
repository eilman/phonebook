package com.demo.phonebook;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long contactId;

    @Column
    private String firstName;

    @Column
    private String lastName;


    public Contact(){

    }

    public Contact(String firstName, String lastName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) &&
                Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName);

    }

    @Override
    public int hashCode() {

        return Objects.hash(contactId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
