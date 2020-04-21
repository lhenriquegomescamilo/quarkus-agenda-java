package com.lhtecnologia.repository;

import com.lhtecnologia.model.Contact;
import com.lhtecnologia.resource.ContactResource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact> {
}
