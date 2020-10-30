package com.bwcompany.publisherauthorbookapp.service;

import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNamePublisherRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SavePublisherRequest;

public interface IPublisherService {
    //...

    Iterable<PublisherDTO> findAllPublisherDTOs();
    Iterable<PublisherDTO> findPublisherDTOsByName(FindByNamePublisherRequest request);
    PublisherDTO savePublisherDTO(SavePublisherRequest request);
}
