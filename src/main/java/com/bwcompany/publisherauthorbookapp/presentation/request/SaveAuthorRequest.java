package com.bwcompany.publisherauthorbookapp.presentation.request;

import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AuthorDTO.class)
public interface SaveAuthorRequest {
    //...
    String getName();
    String getExplanation();
    int getPublisherId();
}
