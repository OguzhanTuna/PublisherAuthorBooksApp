package com.bwcompany.publisherauthorbookapp.presentation;

import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.bwcompany.publisherauthorbookapp.provider.IWrapperMethodProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = BookDTO.class)
public interface SimpleBook extends IWrapperMethodProvider {
    //...

    int getId();
    String getName();
    String getSubName();
    String getSeriesName();
    String getIsbnNo();
    String getExplanation();
    int getAuthorId();
    int getPublisherId();
}
