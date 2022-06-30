package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Author> findAll(String id) {
        Query query = entityManager.createQuery("from Author a where concat(a.id,'') like  :id");

        return query.setParameter("id", "%" + id + "%").getResultList();
    }

    @Override
    public Author findById(int id) {

        return entityManager.find(Author.class, id);
    }

    @Override
    public Author save(Author author) {
        return entityManager.merge(author);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from Author where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
