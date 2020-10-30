package com.bwcompany.publisherauthorbookapp.controller;

import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.bwcompany.publisherauthorbookapp.presentation.SimpleAuthor;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameAuthorRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveAuthorRequest;
import com.bwcompany.publisherauthorbookapp.service.IAuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@ResponseBody
@RequestMapping(path = "/api/author")
public class AuthorController {
    private final IAuthorService m_authorService;
    //...

    //Constructor Injection(No @Autowired annotation since Spring 4.3)
    public AuthorController(IAuthorService authorService)
    {
        m_authorService = authorService;
    }

    @GetMapping(path = "/all")
    public Iterable<SimpleAuthor> findAllSimpleAuthors()
    {
        return StreamSupport.stream(m_authorService.findAllAuthorDTOs().spliterator(), false)
                .map(authorDTO -> (SimpleAuthor)authorDTO)
                .collect(Collectors.toList());
    }

    //Bu method sadece kayıtlı kişileri getirdiği için AuthorDTO' yu destekleyen "SimpleAuthor" interfaceni döndürüyoruz.
    @RequestMapping(path = "/name", method = RequestMethod.POST)
    public Iterable<SimpleAuthor> findSimpleAuthorsByName(@RequestBody FindByNameAuthorRequest request)
    {
        return StreamSupport.stream(m_authorService.findAuthorDTOsByName(request).spliterator(), false)
                .map(authorDTO -> (SimpleAuthor) authorDTO)
                .collect(Collectors.toList());
    }

    //Bu method save methodu oldugu için AuthorDTO dönmelidir yani değiştirilmeden dönmelidir.
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public AuthorDTO saveAuthorDTO(@RequestBody SaveAuthorRequest request)
    {
        return m_authorService.saveAuthorDTO(request);
    }
}
