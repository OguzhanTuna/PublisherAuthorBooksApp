package com.bwcompany.publisherauthorbookapp.service.serviceimpl;

import com.bwcompany.publisherauthorbookapp.data.entity.Book;
import com.bwcompany.publisherauthorbookapp.data.repository.IBookRepository;
import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.bwcompany.publisherauthorbookapp.mapper.IBookMapper;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByIsbnNoBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindBySeriesNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveBookRequest;
import com.bwcompany.publisherauthorbookapp.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service //Default olarak @Scope("singleton") dır. Singleton pattern-eager implementation a uygun şekilde bean
//yaratılır
public class BookService implements IBookService {
    private final IBookRepository m_bookRepository;
    //...

    private Iterable<BookDTO> getBookDTOs(Iterable<Book> books)
    {
        return StreamSupport.stream(books.spliterator(), false)
                .map(IBookMapper.MS_INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
    }

    //Constructor Injection (No @Autowired annotation since Spring 4.3)
    public BookService(IBookRepository bookRepository)
    {
        m_bookRepository = bookRepository;
    }

    @Override
    public Iterable<BookDTO> findAllBookDTOs()
    {
        //sınıfın non-static metotları okunabilirlik açısından "this reference syntax" ile çağırılmalıdır.
        return this.getBookDTOs(m_bookRepository.findAll());
    }

    @Override
    public Iterable<BookDTO> findBookDTOsByName(FindByNameBookRequest request)
    {
        return this.getBookDTOs(m_bookRepository.findByName(request));
    }

    @Override
    public Optional<BookDTO> findByIsbnNo(FindByIsbnNoBookRequest request)
    {
        return m_bookRepository.findByIsbnNo(request.getIsbnNo()).map(IBookMapper.MS_INSTANCE::bookToBookDTO);
    }

    @Override
    public Iterable<BookDTO> findBookBySeriesName(FindBySeriesNameBookRequest request)
    {
        return this.getBookDTOs(m_bookRepository.findBySeriesName(request.getSeriesName()));
    }

    @Override
    public BookDTO saveBookDTO(SaveBookRequest bookDTO)
    {
        return IBookMapper.MS_INSTANCE.bookToBookDTO(m_bookRepository.save(IBookMapper.MS_INSTANCE.save(bookDTO)));
    }
}
