package com.bwcompany.publisherauthorbookapp.service;

import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByIsbnNoBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindBySeriesNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveBookRequest;

import java.util.Optional;

public interface IBookService {
    //...

    Iterable<BookDTO> findAllBookDTOs();
    Iterable<BookDTO> findBookDTOsByName(FindByNameBookRequest request);
    Optional<BookDTO> findByIsbnNo(FindByIsbnNoBookRequest request);
    Iterable<BookDTO> findBookBySeriesName(FindBySeriesNameBookRequest request);
    BookDTO saveBookDTO(SaveBookRequest bookDTO);
}
