package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StatusDAOImpl implements StatusDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Status> findAll() {

        return entityManager.createQuery("from Status",Status.class).getResultList();
    }

    @Override
    public Status findById(int id) {
        return entityManager.find(Status.class,id);
    }

    @Override
    public void save(Status status) {
        entityManager.merge(status);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from Status where id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }

}
