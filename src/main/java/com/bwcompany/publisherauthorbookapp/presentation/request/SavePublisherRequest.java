package com.bwcompany.publisherauthorbookapp.presentation.request;

import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PublisherDTO.class)
public interface SavePublisherRequest {
    //...

    String getName();
    String getExplanation();
}
