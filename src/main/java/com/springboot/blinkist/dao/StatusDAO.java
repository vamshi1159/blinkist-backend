package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Status;

import java.util.List;

public interface StatusDAO {
    List<Status> findAll();

    Status findById(int id);

    void save(Status book);

    void deleteById(int id);
}
