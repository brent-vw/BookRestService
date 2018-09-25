package com.realdolmen.brentvw.rest.controller;

import com.realdolmen.brentvw.rest.domain.Author;
import com.realdolmen.brentvw.rest.repository.AuthorRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("authors")
@Stateless
public class AuthorController {
    @Inject
    private AuthorRepository authorRepository;

    @Context
    private UriInfo uriInfo;

    private UriBuilder authorCreated;

    @PostConstruct
    public void init() {
        this.authorCreated = uriInfo.getBaseUriBuilder().path("authors").path("{id}");
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAuthor(Author newAuthor) {
        authorRepository.createAuthor(newAuthor);

        return Response.created(authorCreated.build(newAuthor.getId()))
                .build();
    }

    @GET
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Author getAuthor(@PathParam("id") int id) {
        return authorRepository.findAuthorById(id);
    }
}
