package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LibraryDAOImpl implements LibraryDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Library> findAll() {
        Query query = entityManager.createQuery("from Library where user_name=:userName")
                .setParameter("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return query.getResultList();
    }

    @Override
    public Library findById(int id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public Library save(Library library) {

        return entityManager.merge(library);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from Library where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
