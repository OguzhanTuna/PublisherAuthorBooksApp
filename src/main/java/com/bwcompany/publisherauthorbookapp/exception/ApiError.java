package com.bwcompany.publisherauthorbookapp.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiError {
    private final String m_msg;
    private final Object m_rejectedValue;
    private final HttpStatus m_status;
    //...

    protected ApiError(String msg, Object rejectedValue, HttpStatus status)
    {
        m_msg = msg;
        m_rejectedValue = rejectedValue;
        m_status = status;
    }

    public String getMessage()
    {
        return m_msg;
    }

    public Object getValue()
    {
        return m_rejectedValue;
    }

    public HttpStatus getStatus()
    {
        return m_status;
    }
}
