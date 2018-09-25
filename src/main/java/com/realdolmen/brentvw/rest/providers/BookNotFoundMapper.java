package com.realdolmen.brentvw.rest.providers;

import com.realdolmen.brentvw.rest.controller.BookNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BookNotFoundMapper implements ExceptionMapper<BookNotFoundException> {
    @Override
    public Response toResponse(BookNotFoundException exception) {
        return Response.status(404).entity("<html><body><h1>Resource not found</h1><img src=\"https://media.giphy.com/media/72DtXz1K8PTOw/giphy.gif\" /></body></html>").build();
    }
}
