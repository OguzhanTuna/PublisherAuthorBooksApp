package com.bwcompany.publisherauthorbookapp.dto;

import com.bwcompany.publisherauthorbookapp.presentation.SimplePublisher;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNamePublisherRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SavePublisherRequest;

public class PublisherDTO implements FindByNamePublisherRequest, SavePublisherRequest, SimplePublisher {
    private int m_id;
    private String m_name;
    private String m_explanation;
    //...

    public PublisherDTO() {}

    public PublisherDTO(int id, String name, String explanation)
    {
        m_id = id;
        m_name = name;
        m_explanation = explanation;
    }

    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getExplanation()
    {
        return m_explanation;
    }

    public void setExplanation(String explanation)
    {
        m_explanation = explanation;
    }
}
