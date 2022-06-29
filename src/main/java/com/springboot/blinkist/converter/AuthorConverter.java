package com.springboot.blinkist.converter;

import com.springboot.blinkist.dto.AuthorDTO;
import com.springboot.blinkist.entity.Author;
import com.springboot.blinkist.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorConverter {

    private static final Logger LOGGER = LogManager.getLogger(AuthorConverter.class);

    private AuthorConverter() {

    }

    public static Author authorDTOtoEntity(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            String message = "no author found";
            LOGGER.warn(message);
            throw new NotFoundException(message);
        }
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName().toLowerCase());
        author.setLastName(authorDTO.getLastName().toLowerCase());
        return author;
    }

    public static AuthorDTO entityToauthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName().toLowerCase());
        authorDTO.setLastName(author.getLastName().toLowerCase());
        return authorDTO;
    }

    public static List<AuthorDTO> entityToauthorDTO(List<Author> authors) {
        return authors.stream().map(AuthorConverter::entityToauthorDTO).collect(Collectors.toList());
    }
}
