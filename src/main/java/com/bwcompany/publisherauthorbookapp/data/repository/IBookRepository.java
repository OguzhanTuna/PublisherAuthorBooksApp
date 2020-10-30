package com.bwcompany.publisherauthorbookapp.data.repository;

import com.bwcompany.publisherauthorbookapp.data.entity.Book;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.util.data.ICrudRepository;

import java.util.Optional;

public interface IBookRepository extends ICrudRepository<Book, Integer> {
    //...
    Iterable<Book> findByName(FindByNameBookRequest request);
    Iterable<Book> findBySeriesName(String seriesName);
    Optional<Book> findByIsbnNo(String isbnNo);
}
