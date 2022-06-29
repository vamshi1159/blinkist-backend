package com.springboot.blinkist.service;

import com.springboot.blinkist.converter.AuthorConverter;
import com.springboot.blinkist.dao.AuthorDAO;
import com.springboot.blinkist.dto.AuthorDTO;
import com.springboot.blinkist.entity.Author;
import com.springboot.blinkist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public List<AuthorDTO> findAll(String id) {
        List<Author> authors = authorDAO.findAll(id);
        return AuthorConverter.entityToauthorDTO(authors);
    }

    @Override
    @Transactional
    public AuthorDTO findById(int id) {

        Author author =authorDAO.findById(id);
        if(author == null)
        {
            throw new NotFoundException("no author found with id: "+id);
        }
        return AuthorConverter.entityToauthorDTO(author);
    }

    @Override
    @Transactional
    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = authorDAO.save(AuthorConverter.authorDTOtoEntity(authorDTO));
        authorDTO.setId(author.getId());
        return authorDTO;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        authorDAO.deleteById(id);
    }
}
