package com.bwcompany.publisherauthorbookapp.presentation;

import com.bwcompany.publisherauthorbookapp.dto.AuthorDTO;
import com.bwcompany.publisherauthorbookapp.provider.IWrapperMethodProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(as = AuthorDTO.class)
public interface SimpleAuthor extends IWrapperMethodProvider {
    //...

    int getId();
    String getName();
    String getExplanation();
}
