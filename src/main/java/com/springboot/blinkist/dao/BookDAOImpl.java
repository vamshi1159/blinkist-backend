package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Book> findAll() {
        //get session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Book> query = currentSession.createQuery("from Book",Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book findById(int id) {
        //get session
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Book.class,id);
    }

    @Override
    @Transactional
    public void save(Book book) {
        //get session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        //get session
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("delete from Book where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
