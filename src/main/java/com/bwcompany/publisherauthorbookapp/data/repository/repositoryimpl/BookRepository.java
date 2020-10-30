package com.bwcompany.publisherauthorbookapp.data.repository.repositoryimpl;

import com.bwcompany.publisherauthorbookapp.data.entity.Book;
import com.bwcompany.publisherauthorbookapp.data.repository.IBookRepository;

import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.*;
import java.util.stream.IntStream;

@Repository //Bir class' ın Entity Class' ına karşılık gelen Repository class' ı olduğunu bildirmek için
//@Repository annotation' ı ile işaretlenir.
//@Repository annotation' ı aynı zamanda @Component tır.
//Ve @Repository annotation' ı ile işaretlenen class' ın bean' i default olarak @Scope("singleton")
//olduğundan "singleton pattern-eager implementation"' a uygun şekilde yaratılır.
public class BookRepository implements IBookRepository {
    //EntityManager interface ini destekleyen anonymous veya concrete bir class' ın nesnesinin adresini
    //inject(atama yaptık) ettik.
    @PersistenceContext //Eğer Spring Bean Container dan otomatik olarak EntityManager interface ini destekleyen bir anonymous
    //veya concrete class' ın nesnesinin adresini alırsak otomatik olarak Transaction başlatılır.
    //Fakat manuel olarak alırsak Transaction' ı bizim başlatmamız gerekir.
    private EntityManager m_entityManager;

    private static final String MS_FINDALL_QL = "select books from Book books";
    private static final String MS_FINDBYISBNNO_QL = "select books from Book books where books.isbnNo like :isbnNoParam";
    private static final String MS_FINDBYNAME_QL = "select books from Book books where books.name like :nameParam";
    private static final String MS_FINDBYSERIESNAME_QL = "select books from Book books where books.seriesName like :seriesNameParam";
    //...

    /*private final EntityManager getEntityManager()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("src/main/resources/META-INF/persistent.xml");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }*/

    /*private void flushAndClear(EntityManager entityManager)
    {
        entityManager.flush();
        entityManager.clear();
    }*/

    private Book saveForBook(Book entity)
    {
        m_entityManager.persist(entity);

        return entity;
    }

    private void deleteForBook(Book entity)
    {
        Book book = m_entityManager.find(Book.class, entity.getId());

        m_entityManager.remove(book);

        m_entityManager.flush();
    }

    private List getResultForAllBooksWithCreateQuery(String ql)
    {
        Query query = m_entityManager.createQuery(ql);

        return query.getResultList();
    }

    //Varargs parametreler(elipsis) arka planda bir dizi nesnesi oluşturur ve parametrik yapıda en sona yazılmalıdır.
    private List<Book> getResultForArgsBooksWithCreateQuery(String ql, String[] paramsName, Object...args)
    {
        Query query = m_entityManager.createQuery(ql);

        if(args.length != 0) {
            IntStream.range(0, args.length).forEach(index -> query.setParameter(paramsName[index], args[index]));
        }

        return query.getResultList();
    }

    private List<Book> getResultForArgsBooksWithCriteriaQuery(FindByNameBookRequest request)
    {
        CriteriaBuilder cb = m_entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);

        Root<Book> from = query.from(Book.class);

        Set<Predicate> predicates = new HashSet<>();

        Optional.ofNullable(request.getName())
                .filter(bookName -> !bookName.isEmpty())
                .ifPresent(bookName -> {
                    Predicate bookNamePredicate = cb.like(from.get("name"), "%" + bookName + "%");
                    predicates.add(bookNamePredicate);
                });

        Optional.ofNullable(request.getAuthorName())
                .filter(authorName -> !authorName.isEmpty())
                .ifPresent(authorName -> {
                    from.fetch("author", JoinType.INNER);

                    Predicate authorNamePredicate = cb.like(from.get("author").get("name"), "%" + authorName + "%");
                    predicates.add(authorNamePredicate);
                });

        query.where(predicates.toArray(new Predicate[]{}));

        return m_entityManager.createQuery(query).getResultList();
    }

    public BookRepository()
    {
        /*m_entityManager = this.getEntityManager();*/
    }

    //Error dan türeyenleri yakalamayız
    @Override
    @Transactional
    public void delete(Book entity)
    {
        this.deleteForBook(entity);
    }

    @Override
    @Transactional(readOnly = true) //Read only transaction lar "Dirty Check" uygulamaz. Ve DBMS üzerinden Database' e değişiklik gerçekleştirmez.
    public Iterable<Book> findAll() //Dirty Check= Entity Class' ının nesnesi ilk defa persistent olduğunda kaydedilir. Daha sonra EntityManager da sarf edildikten sonra oluşan nesne ile değişikliğe bakılır. Eğer değişiklik varsa bunları veri tabanına yanısıtır.
    {
        return this.getResultForAllBooksWithCreateQuery(MS_FINDALL_QL);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByIsbnNo(String isbnNo)
    {
        List<Book> booksList = this.getResultForArgsBooksWithCreateQuery(MS_FINDBYISBNNO_QL, new String[]{"isbnNoParam"}, isbnNo);

        return Optional.ofNullable(booksList.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> findByName(FindByNameBookRequest request)
    {
        List<Book> booksList = this.getResultForArgsBooksWithCriteriaQuery(request);

        return booksList;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> findBySeriesName(String seriesName)
    {
        List<Book> booksList = this.getResultForArgsBooksWithCreateQuery(MS_FINDBYSERIESNAME_QL, new String[]{"seriesNameParam"}, seriesName);

        return booksList;
    }

    @Override
    @Transactional
    public Book save(Book entity)
    {
        return this.saveForBook(entity);
    }

    //////////
    //Java da her zaman implementation ını yapmadığım yani factorunu(süslü parantez içini) doldurmadığım metotların
    //"UnsupportedOperationException" fırlatmasını sağlarım
    @Override
    public long count()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Iterable<? extends Book> entities)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Book> findById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E extends Book> E saveAll(Iterable<E> entites)
    {
        throw new UnsupportedOperationException();
    }
}
