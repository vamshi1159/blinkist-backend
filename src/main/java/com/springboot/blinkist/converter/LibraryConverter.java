package com.springboot.blinkist.converter;

import com.springboot.blinkist.dto.LibraryDTO;
import com.springboot.blinkist.dto.LibraryDetailedDTO;
import com.springboot.blinkist.entity.Library;
import com.springboot.blinkist.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryConverter {
    private static final Logger LOGGER = LogManager.getLogger(LibraryConverter.class);
    private LibraryConverter(){

    }
    public static Library libraryDTOtoEntity(LibraryDTO libraryDTO) {
        if(libraryDTO == null)
        {
            String message = "no library found";
            LOGGER.warn(message);
            throw new NotFoundException("no library found");
        }
        Library library = new Library();
        library.setId(libraryDTO.getId());
        library.setUserName(libraryDTO.getUserName());
        return library;
    }

    public static LibraryDetailedDTO entityToLibraryDetailedDTO(Library library) {
        LibraryDetailedDTO libraryDetailedDTO = new LibraryDetailedDTO();
        libraryDetailedDTO.setId(library.getId());
        libraryDetailedDTO.setUserName(library.getUserName());
        libraryDetailedDTO.setAuthor(library.getBook().getAuthor().getFirstName() + " " + library.getBook().getAuthor().getLastName());
        libraryDetailedDTO.setBookTitle(library.getBook().getTitle());
        libraryDetailedDTO.setCategory(library.getBook().getCategory().getLabel());
        libraryDetailedDTO.setStatus(library.getStatus().getLabel());
        return libraryDetailedDTO;
    }

    public static List<LibraryDetailedDTO> entityToLibraryDetailedDTO(List<Library> libraries) {
        return libraries.stream().map(LibraryConverter::entityToLibraryDetailedDTO).collect(Collectors.toList());
    }
}
