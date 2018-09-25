package com.realdolmen.brentvw.rest.repository;

import com.realdolmen.brentvw.rest.domain.Book;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Named
public class BookRepository {
    @PersistenceContext
    private EntityManager em;

    public Book findBookById(Integer id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAllBooks() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    public void createBook(Book book) {
        em.persist(book);
    }

    public void updateBook(Book b) {
        em.merge(b);
    }

    public void deleteBook(Integer id) {
        em.remove(findBookById(id));
    }
}
