package com.springboot.blinkist.service;

import com.springboot.blinkist.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> findAll(String id);

    AuthorDTO findById(int id);

    AuthorDTO save(AuthorDTO author);

    void deleteById(int id);
}
