package com.realdolmen.brentvw.rest.repository;

import com.realdolmen.brentvw.rest.domain.Author;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Named
public class AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    public Author findAuthorById(Integer id) {
        return em.find(Author.class, id);
    }

    public List<Author> findAllAuthors() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    public void createAuthor(Author author) {
        em.persist(author);
    }

    public void updateAuthor(Author author) {
        em.merge(author);
    }

    public void deletAuthor(Integer id) {
        em.remove(findAuthorById(id));
    }
}
