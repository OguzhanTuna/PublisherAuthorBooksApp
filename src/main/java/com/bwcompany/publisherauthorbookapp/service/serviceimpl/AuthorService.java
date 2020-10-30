package com.bwcompany.publisherauthorbookapp.service.serviceimpl;

import com.bwcompany.publisherauthorbookapp.data.repository.IAuthorRepository;
import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.bwcompany.publisherauthorbookapp.mapper.IAuthorMapper;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameAuthorRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveAuthorRequest;
import com.bwcompany.publisherauthorbookapp.service.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service //Default olarak @Scope("singleton") dır. Singleton pattern-eager loading' e uygun şekilde yaratılır.
public class AuthorService implements IAuthorService {
    private final IAuthorRepository m_authorRepository;
    //...

    public AuthorService(IAuthorRepository authorRepository)
    {
        m_authorRepository = authorRepository;
    }

    @Override
    public Iterable<AuthorDTO> findAuthorDTOsByName(FindByNameAuthorRequest request)
    {
        return StreamSupport.stream(m_authorRepository.findByName(request.getName()).spliterator(), false)
                .map(IAuthorMapper.MS_INSTANCE::authorToAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<AuthorDTO> findAllAuthorDTOs()
    {
        return StreamSupport.stream(m_authorRepository.findAll().spliterator(), false)
                .map(IAuthorMapper.MS_INSTANCE::authorToAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO saveAuthorDTO(SaveAuthorRequest request)
    {
        return IAuthorMapper.MS_INSTANCE.authorToAuthorDTO(m_authorRepository.save(IAuthorMapper.MS_INSTANCE.save(request)));
    }
}
