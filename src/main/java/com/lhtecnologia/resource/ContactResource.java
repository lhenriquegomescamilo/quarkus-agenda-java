package com.lhtecnologia.resource;

import com.lhtecnologia.model.Contact;
import com.lhtecnologia.repository.ContactRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactRepository contactRepository;

    @Inject
    public ContactResource(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @POST
    @Transactional
    public Response create(@RequestBody final Contact contact) {
        contactRepository.persist(contact);
        return Response.ok(contact).build();
    }

    @GET
    public Response findAll() {
        final PanacheQuery<Contact> all = contactRepository.findAll();
        return Response.ok(all.list()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long id) {
        final Optional<Contact> byIdOptional = contactRepository.findByIdOptional(id);
        return byIdOptional.map(Response::ok)
                .orElseGet(Response::noContent)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") final Long id) {
        final Optional<Contact> byIdOptional = contactRepository.findByIdOptional(id);
        return byIdOptional
                .map(this::deleteByContact)
                .map(Response::ok)
                .orElseGet(Response::noContent)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateById(
            @PathParam("id") final Long id,
            @RequestBody final Contact contacRequest
    ) {
        Contact byId = contactRepository.findById(id);
        byId.setId(id);
        byId.setName(contacRequest.getName());
        byId.setName(contacRequest.getPhone());
        return Response.ok(byId).build();
    }

    private Contact deleteByContact(final Contact contact) {
        contactRepository.delete(contact);
        return contact;
    }
}