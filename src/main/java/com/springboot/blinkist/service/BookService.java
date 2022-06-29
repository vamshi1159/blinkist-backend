package com.springboot.blinkist.service;

import com.springboot.blinkist.dto.BookDTO;
import com.springboot.blinkist.dto.BookDetailedDTO;
import com.springboot.blinkist.exception.NotFoundException;

import java.util.List;

public interface BookService {
    List<BookDetailedDTO> findByQuery(String id,String author,String category);

    BookDetailedDTO findById(int id);

    BookDTO save(BookDTO book) throws NotFoundException;

    void deleteById(int id);
}
