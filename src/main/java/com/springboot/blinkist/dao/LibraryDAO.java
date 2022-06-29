package com.springboot.blinkist.dao;

import com.springboot.blinkist.entity.Library;

import java.util.List;

public interface LibraryDAO {

    List<Library> findAll();

    Library findById(int id);

    Library save(Library libraryDTO);

    void deleteById(int id);
}
