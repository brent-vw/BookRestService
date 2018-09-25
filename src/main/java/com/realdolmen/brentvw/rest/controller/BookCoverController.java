package com.realdolmen.brentvw.rest.controller;

import com.realdolmen.brentvw.rest.domain.Book;
import com.realdolmen.brentvw.rest.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("images")
public class BookCoverController {
    @Inject
    private BookRepository bookRepository;

    @POST
    @Path("{bookId}")
    @Consumes("image/png")
    public void addCover(@PathParam("bookId") Integer bookId, byte[] image) {
        Book book = bookRepository.findBookById(bookId);
        book.setCoverImage(image);
        bookRepository.updateBook(book);
    }

    @GET
    @Path("{bookId}")
    @Produces("image/png")
    public byte[] getCover(@PathParam("bookId") Integer bookId) {
        return bookRepository.findBookById(bookId).getCoverImage();
    }

}
