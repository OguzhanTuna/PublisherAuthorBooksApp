package com.bwcompany.publisherauthorbookapp.exception;

import org.springframework.http.HttpStatus;

public class BookControllerError extends ApiError{
    //...

    public BookControllerError(String msg, Object rejectedValue, HttpStatus status)
    {
        //super ctor syntax.
        super(msg, rejectedValue, status);
    }

    //...
}
