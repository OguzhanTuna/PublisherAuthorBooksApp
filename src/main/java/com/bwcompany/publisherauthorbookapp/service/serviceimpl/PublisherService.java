package com.bwcompany.publisherauthorbookapp.service.serviceimpl;

import com.bwcompany.publisherauthorbookapp.data.repository.IPublisherRepository;
import com.bwcompany.publisherauthorbookapp.dto.PublisherDTO;
import com.bwcompany.publisherauthorbookapp.mapper.IPublisherMapper;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNamePublisherRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SavePublisherRequest;
import com.bwcompany.publisherauthorbookapp.service.IPublisherService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PublisherService implements IPublisherService {
    //Veri elemanları deleyici optimizasyonundan kurtulmak için "final" tanımlanır.
    //Final tanımlanan veri elemanları "default değer alıcak" konuma düşmemelilerdir.
    //Non-static final veri elemanları bildirim anında, ctor, non-static initializer da ilk değerini alır.
    //static final veri elemanları bildirim anında ve static-initializer de ilk değerini alır.
    //Final keyword' u "always applicable" dır. Yani seçenekler arasında uygulanabilirse kesinlikle
    //uygulanmalıdır.
    private final IPublisherRepository m_publisherRepository;
    //...

    //Constructor Injection(No @Autowired annotation since Spring 4.3)
    public PublisherService(IPublisherRepository publisherRepository)
    {
        m_publisherRepository = publisherRepository;
    }

    @Override
    public Iterable<PublisherDTO> findAllPublisherDTOs()
    {
        return StreamSupport.stream(m_publisherRepository.findAll().spliterator(), false)
                .map(IPublisherMapper.MS_INSTANCE::publisherToPublisherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PublisherDTO> findPublisherDTOsByName(FindByNamePublisherRequest request)
    {
        return StreamSupport.stream(m_publisherRepository.findByName(request.getName()).spliterator(), false)
                .map(IPublisherMapper.MS_INSTANCE::publisherToPublisherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDTO savePublisherDTO(SavePublisherRequest request)
    {
        return IPublisherMapper.MS_INSTANCE.publisherToPublisherDTO(m_publisherRepository.save(IPublisherMapper.MS_INSTANCE.save(request)));
    }
}

