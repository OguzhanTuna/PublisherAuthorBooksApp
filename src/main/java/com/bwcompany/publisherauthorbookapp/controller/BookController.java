package com.bwcompany.publisherauthorbookapp.controller;

import com.bwcompany.publisherauthorbookapp.dto.BookDTO;
import com.bwcompany.publisherauthorbookapp.presentation.SimpleBook;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByIsbnNoBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindBySeriesNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveBookRequest;
import com.bwcompany.publisherauthorbookapp.service.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController //MVC mimarisine göre tasarlanan Application Layer(WEB Layer) ın içinde controller mantıksal katmanı
//olduğunu belirmek için @RestController annotation ını veya @Controller annotation' ını kullanırız. @Controller kullanıcaksak
//yanında @ResponseBody annotation' ını da kullanmalıyız. @ResponseBody annotation' ı Action Methodların döndüğü nesne adresini
//200 status codeuyla ResponseEntity<T> class' ı ile mocklamaya yarar.
@RequestMapping(path = "/api/book")
public class BookController {
    private final IBookService m_bookService;
    //...

    private Iterable<SimpleBook> getSimpleBooks(Iterable<BookDTO> bookDTOs)
    {
        return StreamSupport.stream(bookDTOs.spliterator(), false)
                .map(bookDTO -> (SimpleBook)bookDTO)
                .collect(Collectors.toList());
    }

    //Constructor Injection (No @Autowired annotation since Spring 4.3)
    public BookController(IBookService bookService)
    {
        m_bookService = bookService;
    }

    @GetMapping(path = "/all") //Action method subURL or path
    public Iterable<SimpleBook> findAllSimpleBooks()
    {
        return this.getSimpleBooks(m_bookService.findAllBookDTOs());
    }

    @RequestMapping(path = "/name", method = RequestMethod.POST)
    public Iterable<SimpleBook> findSimpleBooksByName(@RequestBody FindByNameBookRequest request)
    {
        return this.getSimpleBooks(m_bookService.findBookDTOsByName(request));
    }

    @RequestMapping(path = "/isbn", method = RequestMethod.POST)
    public ResponseEntity<SimpleBook> findSimpleBookByIsbnNo(@RequestBody FindByIsbnNoBookRequest request)
    {
        return m_bookService.findByIsbnNo(request)
                .map(bookDTO -> (SimpleBook)bookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @PostMapping(path = "/series")
    public Iterable<SimpleBook> findSimpleBooksBySeriesName(@RequestBody FindBySeriesNameBookRequest request)
    {
        return this.getSimpleBooks(m_bookService.findBookBySeriesName(request));
    }

    @PostMapping(path = "/save")
    public BookDTO saveBookDTO(@RequestBody SaveBookRequest request)
    {
        return m_bookService.saveBookDTO(request);
    }
}
