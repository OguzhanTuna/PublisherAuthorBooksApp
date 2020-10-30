package com.bwcompany.publisherauthorbookapp.data.repository;


import com.bwcompany.publisherauthorbookapp.data.entity.Author;
import com.bwcompany.util.data.ICrudRepository;


public interface IAuthorRepository extends ICrudRepository<Author, Integer> {
    //...
    Iterable<Author> findByName(String name);
}
