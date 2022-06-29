package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> findAll(String id, String author, String category) {

        Query query1 = entityManager.createNativeQuery("select b.id ,b.author_id,b.category_id,b.title,b.image,b.category_id from book b left outer join author a on b.author_id=a.id left outer join category c on b.category_id=c.id " +
                "where concat(b.id,'') like :id and concat(a.first_name,' ',a.last_name) like :author and c.label like :category ", Book.class);
        query1.setParameter("id", "%" + id + "%").setParameter("author", "%" + author + "%").setParameter("category", "%" + category + "%");

        return query1.getResultList();

    }

    @Override
    public Book findById(int id) {
       return entityManager.find(Book.class, id);
    }

    @Override
    public Book save(Book book) {
        Book newBook = entityManager.merge(book);
        book.setId(newBook.getId());
        return book;
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Book where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
