package com.bwcompany.publisherauthorbookapp.data.repository;

import com.bwcompany.publisherauthorbookapp.data.entity.Publisher;
import com.bwcompany.util.data.ICrudRepository;

public interface IPublisherRepository extends ICrudRepository<Publisher, Integer> {
    //...

    Iterable<Publisher> findByName(String name);
}
