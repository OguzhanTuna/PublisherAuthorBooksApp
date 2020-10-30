package com.bwcompany.publisherauthorbookapp.data.entity;

import javax.persistence.*;


//Database Management System üzerinden erişilen Database deki bir grup verinin bir tanesine karşılık gelen Java Türüne
//"Entity" denir. Entity Class ların JPA de diğer adı "POJO CLASS" lardır.
@Entity //Bunun bir Entity Class' ı olduğunu ve Database Management System seviyesinde bir tabloya tekabul ediceğini
//belitmek için @Entity annotation' ı kullanılır.
@Table(name = "t_book", schema = "publisher") //DBMS seiyesinde yaratılacak tabloya ilişkin çeşitli özellikleri girmek için @Table annotation' ı kullanılır.
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select books from Book books")
})
public class Book {
    @Id //Bu feild' ımızın (Spring Bean de bunlara property derler) DBMS seviyesinde "primary key" olduğunu belirtmek için yazılır.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Bu DBMS system seviyesindeki "primary key" in otomatik artan id olması için yazılır.
    @Column(name = "id", nullable = false) //Sonuçta bu field DBMS seviyesindeki "books" tablosunda bir column a denk gelecektir. Bu column a ilşkin bilgiler @Column anootation' ı ile girilir.
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_name", nullable = false)
    private String subName;

    @Column(name = "series_name", nullable = false)
    private String seriesName;

    @Column(name = "isbn_no", nullable = false)
    private String isbnNo;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Column(name = "author_id", insertable = false, updatable = false, nullable =  false)
    private int authorId;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Author author;

    @Column(name = "publisher_id", insertable = false, updatable = false, nullable = false)
    private int publisherId;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publisher publisher;
    //...

    public Book() {}

    public Book(int id, String name, String subName, String seriesName, String isbnNo, String explanation,
                int authorId, int publisherId)
    {
        this.id = id;
        this.name = name;
        this.subName = subName;
        this.seriesName = seriesName;
        this.isbnNo = isbnNo;
        this.explanation = explanation;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public String getSeriesName()
    {
        return seriesName;
    }

    public void setSeriesName(String seriesName)
    {
        this.seriesName = seriesName;
    }

    public String getIsbnNo()
    {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo)
    {
        this.isbnNo = isbnNo;
    }

    public String getExplanation()
    {
        return explanation;
    }

    public void setExplanation(String explanation)
    {
        this.explanation = explanation;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public int getPublisherId()
    {
        return publisherId;
    }

    public Publisher getPublisher()
    {
        return publisher;
    }

    public void setPublisherId(int publisherId)
    {
        this.publisherId = publisherId;
    }

    public void setPublisher(Publisher publisher)
    {
        this.publisher = publisher;
    }

    @Override
    public String toString()
    {
        return String.format("IsbnNo: %s, Name: %s", isbnNo, name);
    }
}
