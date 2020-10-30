package com.bwcompany.publisherauthorbookapp.service;

import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameAuthorRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveAuthorRequest;

public interface IAuthorService {
    //...

    Iterable<AuthorDTO> findAllAuthorDTOs();
    Iterable<AuthorDTO> findAuthorDTOsByName(FindByNameAuthorRequest request);
    AuthorDTO saveAuthorDTO(SaveAuthorRequest request);
}
