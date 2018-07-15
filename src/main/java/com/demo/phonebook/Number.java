package com.demo.phonebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Number {
    @Id
    @GeneratedValue
    private Long numberId;

    @Column
    private String number;

    @Column
    private String which;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contactId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Contact contact;

    public Number() {
    }

    public Number(String number, String which, Contact contact) {
        this.number = number;
        this.which = which;
        this.contact = contact;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return Objects.equals(numberId, number1.numberId) &&
                Objects.equals(number, number1.number) &&
                Objects.equals(which, number1.which) &&
                Objects.equals(contact, number1.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberId, number, which, contact);
    }

    @Override
    public String toString() {
        return "Number{" +
                "numberId=" + numberId +
                ", number='" + number + '\'' +
                ", which='" + which + '\'' +
                ", contact=" + contact +
                '}';
    }
}
