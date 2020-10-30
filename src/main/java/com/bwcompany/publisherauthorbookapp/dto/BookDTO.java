package com.bwcompany.publisherauthorbookapp.dto;

import com.bwcompany.publisherauthorbookapp.presentation.SimpleBook;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByIsbnNoBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindByNameBookRequest;
import com.bwcompany.publisherauthorbookapp.presentation.request.FindBySeriesNameBookRequest;

public class BookDTO implements SimpleBook, FindByNameBookRequest, FindBySeriesNameBookRequest, FindByIsbnNoBookRequest {
    private int m_id;
    private String m_name;
    private String m_subName;
    private String m_seriesName;
    private String m_isbnNo;
    private String m_explanation;
    private int m_authorId;
    private int m_publisherId;
    //...

    public BookDTO() {}

    public BookDTO(int id, String name, String subName, String seriesName, String isbnNo, String explanation,
                   int authorId, int publisherId)
    {
        m_id = id;
        m_name = name;
        m_subName = subName;
        m_seriesName = seriesName;
        m_isbnNo = isbnNo;
        m_explanation = explanation;
        m_authorId = authorId;
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

    @Override
    public String getAuthorName()
    {
        return null;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getSubName()
    {
        return m_subName;
    }

    public void setSubName(String subName)
    {
        m_subName = subName;
    }

    public String getSeriesName()
    {
        return m_seriesName;
    }

    public void setSeriesName(String seriesName)
    {
        m_seriesName = seriesName;
    }

    public String getIsbnNo()
    {
        return m_isbnNo;
    }

    public void setIsbnNo(String isbnNo)
    {
        m_isbnNo = isbnNo;
    }

    public String getExplanation()
    {
        return m_explanation;
    }

    public void setExplanation(String explanation)
    {
        m_explanation = explanation;
    }

    public int getAuthorId()
    {
        return m_authorId;
    }

    public void setAuthorId(int authorId)
    {
        m_authorId = authorId;
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
