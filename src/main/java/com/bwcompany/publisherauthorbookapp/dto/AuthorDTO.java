package com.bwcompany.publisherauthorbookapp.dto;

import com.bwcompany.publisherauthorbookapp.presentation.SimpleAuthor;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameAuthorRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.SaveAuthorRequest;

public class AuthorDTO implements SimpleAuthor, FindByNameAuthorRequest, SaveAuthorRequest {
    private int m_id;
    private String m_name;
    private String m_explanation;
    private int m_publisherId;
    //...

    public AuthorDTO() {}

    public AuthorDTO(int id, String name, String explanation, int publisherId)
    {
        m_id = id;
        m_name = name;
        m_explanation = explanation;
        m_publisherId = publisherId;
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

    public int getPublisherId()
    {
        return m_publisherId;
    }

    public void setPublisherId(int publisherId)
    {
        m_publisherId = publisherId;
    }
}
