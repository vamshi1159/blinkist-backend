package com.springboot.blinkist.service;

import com.springboot.blinkist.dao.BookDAO;
import com.springboot.blinkist.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    @Transactional
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Book book) {
            bookDAO.save(book);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
            bookDAO.deleteById(id);
    }
}
