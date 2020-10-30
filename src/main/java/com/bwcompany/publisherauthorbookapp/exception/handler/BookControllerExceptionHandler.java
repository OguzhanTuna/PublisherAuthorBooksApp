package com.bwcompany.publisherauthorbookapp.exception.handler;

import com.bwcompany.util.data.RepositoryException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BookControllerExceptionHandler extends ResponseEntityExceptionHandler {
    //...
}
