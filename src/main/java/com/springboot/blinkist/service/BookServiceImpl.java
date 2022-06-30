package com.springboot.blinkist.service;

import com.springboot.blinkist.converter.BookConverter;
import com.springboot.blinkist.dao.AuthorDAO;
import com.springboot.blinkist.dao.BookDAO;
import com.springboot.blinkist.dao.CategoryDAO;
import com.springboot.blinkist.dto.BookDTO;
import com.springboot.blinkist.dto.BookDetailedDTO;
import com.springboot.blinkist.entity.Author;
import com.springboot.blinkist.entity.Book;
import com.springboot.blinkist.entity.Category;
import com.springboot.blinkist.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<BookDetailedDTO> findByQuery(String id, String author, String category) {
        return BookConverter.entityTobookDetailedDTO(bookDAO.findAll(id, author, category));
    }

    @Override
    @Transactional
    public BookDetailedDTO findById(int id) {
        Book book = bookDAO.findById(id);
        return BookConverter.entityTobookDetailedDTO(book);
    }

    @Override
    @Transactional
    public BookDTO save(BookDTO bookDTO) throws NotFoundException {
        String message;
        Author author = authorDAO.findById(bookDTO.getAuthorId());
        if (author == null) {
            message = String.format("no author found with id: %d ", bookDTO.getAuthorId());
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Category category = categoryDAO.findById(bookDTO.getCategoryId());
        if (category == null) {
            message = String.format("no category found with id: %d", bookDTO.getCategoryId());
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Book book = BookConverter.bookDTOToBook(bookDTO);
        book.setAuthor(author);
        book.setCategory(category);
        Book newBook = bookDAO.save(book);
        bookDTO.setId(newBook.getId());
        return bookDTO;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }
}
