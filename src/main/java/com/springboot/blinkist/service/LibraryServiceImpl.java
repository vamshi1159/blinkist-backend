package com.springboot.blinkist.service;

import com.springboot.blinkist.converter.LibraryConverter;
import com.springboot.blinkist.dao.BookDAO;
import com.springboot.blinkist.dao.LibraryDAO;
import com.springboot.blinkist.dao.StatusDAO;
import com.springboot.blinkist.dto.LibraryDTO;
import com.springboot.blinkist.dto.LibraryDetailedDTO;
import com.springboot.blinkist.entity.Book;
import com.springboot.blinkist.entity.Library;
import com.springboot.blinkist.entity.Status;
import com.springboot.blinkist.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LogManager.getLogger(LibraryServiceImpl.class);
    @Autowired
    private LibraryDAO libraryDAO;
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Override
    @Transactional
    public List<LibraryDetailedDTO> findByQuery(String status) {
        return LibraryConverter.entityToLibraryDetailedDTO(libraryDAO.findAll())
                .stream()
                .filter((libraryDetailedDTO -> libraryDetailedDTO.getStatus().contains(status)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LibraryDetailedDTO findById(int id) {

        Library library = libraryDAO.findById(id);
        if (library == null) {
            String message = String.format("no library item found with id: %d", id);
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        return LibraryConverter.entityToLibraryDetailedDTO(library);
    }

    @Override
    @Transactional
    public LibraryDTO save(LibraryDTO libraryDTO) {
        String message;
        Book book = bookDAO.findById(libraryDTO.getBookId());
        if (book == null) {
            message = String.format("no book found with id: %d", libraryDTO.getBookId());
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Status status = statusDAO.findById(libraryDTO.getStatusId());
        if (status == null) {
            message = String.format("no status found with id: %d", libraryDTO.getStatusId());
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Library library = LibraryConverter.libraryDTOtoEntity(libraryDTO);
        library.setBook(book);
        library.setStatus(status);
        Library newLibrary = libraryDAO.save(library);
        libraryDTO.setId(newLibrary.getId());
        return libraryDTO;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if (libraryDAO.findById(id) == null) {
            throw new NotFoundException("no library item found with id: " + id);
        }
        libraryDAO.deleteById(id);
    }
}
