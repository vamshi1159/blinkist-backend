package com.springboot.blinkist.converter;

import com.springboot.blinkist.dto.BookDTO;
import com.springboot.blinkist.dto.BookDetailedDTO;
import com.springboot.blinkist.entity.Book;
import com.springboot.blinkist.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    private static final Logger LOGGER = LogManager.getLogger(BookConverter.class);
    private BookConverter(){

    }
    public static Book bookDTOToBook(BookDTO bookDTO) {
        if(bookDTO == null)
        {
            String message= "no book found";
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle().toLowerCase());
        book.setImage(bookDTO.getImage());
        return book;
    }

    public static BookDetailedDTO entityTobookDetailedDTO(Book book) {
        BookDetailedDTO bookDetailedDTO = new BookDetailedDTO();
        bookDetailedDTO.setId(book.getId());
        bookDetailedDTO.setTitle(book.getTitle().toLowerCase());
        bookDetailedDTO.setImage(book.getImage());
        bookDetailedDTO.setAuthor(book.getAuthor().getFirstName().toLowerCase() + " " + book.getAuthor().getLastName().toLowerCase());
        bookDetailedDTO.setCategory(book.getCategory().getLabel().toLowerCase());
        return bookDetailedDTO;
    }

    public static List<BookDetailedDTO> entityTobookDetailedDTO(List<Book> books) {
        return books.stream().map(BookConverter::entityTobookDetailedDTO).collect(Collectors.toList());
    }
}
