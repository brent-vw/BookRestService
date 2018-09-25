package com.realdolmen.brentvw.rest.controller;

import com.realdolmen.brentvw.rest.domain.Book;
import com.realdolmen.brentvw.rest.repository.BookRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("books")
@Stateless
public class BookController {
    @Inject
    private BookRepository bookRepository;

    @Context
    private UriInfo uriInfo;

    private UriBuilder bookCreated;

    @PostConstruct
    public void init() {
        this.bookCreated = uriInfo.getBaseUriBuilder().path("books").path("{id}");
    }

    @GET
    @Path("{id}")
    public Book findBookById(@PathParam("id") Integer id) {
        return bookRepository.findBookById(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    @POST
    @Consumes(APPLICATION_XML)
    public Response createBook(Book book) {
        bookRepository.createBook(book);

        return Response.created(bookCreated.build(book.getId()))
                .build();
    }

    @PUT
    public void updateBook(Book b) {
        bookRepository.updateBook(b);
    }

    @DELETE
    @Path("{id}")
    public void deleteBook(@PathParam("id") Integer id) {
        bookRepository.deleteBook(id);
    }
}
