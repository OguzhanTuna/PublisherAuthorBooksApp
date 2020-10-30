package com.bwcompany.publisherauthorbookapp.mapper;

import com.bwcompany.publisherauthorbookapp.data.entity.Book;
import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByIsbnNoBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindBySeriesNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "BookMapperImpl")
public interface IBookMapper {
    //...

    IBookMapper MS_INSTANCE = Mappers.getMapper(IBookMapper.class);

    public BookDTO bookToBookDTO(Book book);

    Book search(FindByNameBookRequest request);
    Book search(FindByIsbnNoBookRequest request);
    Book search(FindBySeriesNameBookRequest request);
    Book save(SaveBookRequest request);
}
