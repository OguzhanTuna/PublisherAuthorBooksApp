package com.bwcompany.publisherauthorbookapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_author", schema = "publisher")
public class Author {
    @Id
    @SequenceGenerator(name = "AuthorIdSeq", initialValue = 1, allocationSize = Integer.MAX_VALUE)
    @GeneratedValue(generator = "AuthorIdSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Column(name = "publisher_id", insertable = false, updatable = false, nullable = false)
    private int publisherId;

    @ManyToOne //Default as FetchType.EAGER
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publisher publisher;
    //...

    public Author() {}

    public Author(int id, String name, String explanation, int publisherId)
    {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
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

    public String getExplanation()
    {
        return explanation;
    }

    public void setExplanation(String explanation)
    {
        this.explanation = explanation;
    }

    public Publisher getPublisher()
    {
        return publisher;
    }

    public void setPublisher(Publisher publisher)
    {
        this.publisher = publisher;
    }

    public int getPublisherId()
    {
        return publisherId;
    }

    public void setPublisherId(int publisherId)
    {
        this.publisherId = publisherId;
    }
}
