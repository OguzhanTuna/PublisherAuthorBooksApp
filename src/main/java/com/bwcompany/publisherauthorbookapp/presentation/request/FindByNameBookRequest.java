package com.bwcompany.publisherauthorbookapp.presentation.request;

import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = BookDTO.class)
public interface FindByNameBookRequest {
    //...

    String getName();
    String getAuthorName();
}
