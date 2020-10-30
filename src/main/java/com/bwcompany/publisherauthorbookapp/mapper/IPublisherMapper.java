package com.bwcompany.publisherauthorbookapp.mapper;

import com.bwcompany.publisherauthorbookapp.data.entity.Publisher;
import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNamePublisherRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SavePublisherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "PublisherMapperImpl")
public interface IPublisherMapper {
    //...

    IPublisherMapper MS_INSTANCE = Mappers.getMapper(IPublisherMapper.class);

    PublisherDTO publisherToPublisherDTO(Publisher publisher);
    Publisher search(FindByNamePublisherRequest request);
    Publisher save(SavePublisherRequest request);
}
