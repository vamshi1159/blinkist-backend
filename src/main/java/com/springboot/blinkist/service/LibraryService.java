package com.springboot.blinkist.service;

import com.springboot.blinkist.dto.LibraryDTO;
import com.springboot.blinkist.dto.LibraryDetailedDTO;

import java.util.List;

public interface LibraryService {
    List<LibraryDetailedDTO> findByQuery(String status);
    LibraryDetailedDTO findById(int id);
    LibraryDTO save(LibraryDTO libraryDTO);
    void deleteById(int id);
}
