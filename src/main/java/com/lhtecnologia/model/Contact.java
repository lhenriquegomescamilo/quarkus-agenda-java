package com.lhtecnologia.model;

import javax.persistence.*;

@Entity(name = "Contact")
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;

    public Contact() {
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact withId(final Long id) {
        this.id = id;
        return this;
    }

    public Contact withName(final String name) {
        this.name = name;
        return this;
    }

    public Contact withPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public static Contact copyValues(Contact contact) {
        return new Contact()
                .withId(contact.getId())
                .withName(contact.getName())
                .withPhone(contact.getPhone());
    }
}
