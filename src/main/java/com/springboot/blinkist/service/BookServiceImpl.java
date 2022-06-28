package com.springboot.blinkist.service;

import com.springboot.blinkist.dao.BookDAO;
import com.springboot.blinkist.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public void save(Book book) {
            bookDAO.save(book);
    }

    @Override
    public void deleteById(int id) {
            bookDAO.deleteById(id);
    }
}
