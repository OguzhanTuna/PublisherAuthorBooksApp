package com.bwcompany.publisherauthorbookapp.mapper;

import com.bwcompany.publisherauthorbookapp.data.entity.Author;
import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameAuthorRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "AuthorMapperImpl")
public interface IAuthorMapper {
    //...

    IAuthorMapper MS_INSTANCE = Mappers.getMapper(IAuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author authorDTO);
    Author save(SaveAuthorRequest request);
    Author search(FindByNameAuthorRequest request);
}
