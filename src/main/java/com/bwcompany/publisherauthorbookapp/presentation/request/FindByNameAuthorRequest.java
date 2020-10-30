package com.bwcompany.publisherauthorbookapp.presentation.request;

import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(as = AuthorDTO.class) //Artık akış DispatcherServlet' a geldiğinde map işlemi "AuthorDTO" class' ına gerçekleştirilir.
public interface FindByNameAuthorRequest {
    //...
    String getName();
}
