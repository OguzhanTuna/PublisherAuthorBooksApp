package com.bwcompany.publisherauthorbookapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_publisher", schema = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "explanation", nullable = false)
    private String explanation;
    //...

    //Default ctor. Eğer sınıfın default durumu varsa default ctor yazılmalıdır.
    public Publisher() {}

    public Publisher(int id, String name, String explanation)
    {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
    }

    public int getId()
    {
        return this.id;
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
}
