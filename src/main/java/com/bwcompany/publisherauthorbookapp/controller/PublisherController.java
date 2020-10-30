package com.bwcompany.publisherauthorbookapp.controller;

import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.bwcompany.publisherauthorbookapp.presentation.SimplePublisher;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNamePublisherRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SavePublisherRequest;
import com.bwcompany.publisherauthorbookapp.service.IPublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/api/publisher")
public class PublisherController {
    private final IPublisherService m_publisherService;
    //...

    //Constructor Injection (No @Autowired annotation since Spring 4.3)
    public PublisherController(IPublisherService publisherService)
    {
        m_publisherService = publisherService;
    }

    @GetMapping(path = "/all")
    public Iterable<SimplePublisher> findAllSimplePublishers()
    {
        return StreamSupport.stream(m_publisherService.findAllPublisherDTOs().spliterator(), false)
                .map(publisherDTO -> (SimplePublisher) publisherDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/name/{byname}", method = RequestMethod.POST)
    public Iterable<SimplePublisher> findSimplePublishersByName(@RequestBody @PathVariable FindByNamePublisherRequest request)
    {
        return StreamSupport.stream(m_publisherService.findPublisherDTOsByName(request).spliterator(), false)
                .map(publisherDTO -> (SimplePublisher) publisherDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/save") //Action method subURL or path
    public PublisherDTO savePublisherDTO(SavePublisherRequest request)
    {
        return m_publisherService.savePublisherDTO(request);
    }
}
