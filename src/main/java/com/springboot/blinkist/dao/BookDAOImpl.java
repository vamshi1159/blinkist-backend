package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        Query query = entityManager.createQuery("from Book");
        return query.getResultList();
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    public void save(Book book) {
        Book book1=entityManager.merge(book);
        book.setId(book1.getId());
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Book where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
