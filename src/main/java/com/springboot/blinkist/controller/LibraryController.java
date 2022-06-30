package com.springboot.blinkist.controller;


import com.springboot.blinkist.dto.LibraryDTO;
import com.springboot.blinkist.dto.LibraryDetailedDTO;
import com.springboot.blinkist.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<LibraryDetailedDTO> getLibrary(@RequestParam(value = "status", required = false, defaultValue = "") String status) {
        return libraryService.findByQuery(status);
    }

    @PostMapping
    public LibraryDTO addBookToLibrary(@RequestBody LibraryDTO libraryDTO) {
        return saveOrUpdate(libraryDTO);
    }

    @PutMapping
    public LibraryDTO update(@RequestBody LibraryDTO libraryDTO) {
        return saveOrUpdate(libraryDTO);
    }

    private LibraryDTO saveOrUpdate(LibraryDTO libraryDTO){
        libraryDTO.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        return libraryService.save(libraryDTO);
    }

}
