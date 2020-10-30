package com.bwcompany.publisherauthorbookapp.presentation;

import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.bwcompany.publisherauthorbookapp.provider.IWrapperMethodProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = PublisherDTO.class)
public interface SimplePublisher extends IWrapperMethodProvider {
    //...

    int getId();
    String getName();
    String getExplanation();
}
