package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("from Category").getResultList();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void save(Category category) {
        entityManager.merge(category);
    }
}
