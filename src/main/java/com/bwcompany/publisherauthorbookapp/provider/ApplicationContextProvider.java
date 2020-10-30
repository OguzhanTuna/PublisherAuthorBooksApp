package com.bwcompany.publisherauthorbookapp.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext ms_context;
    //...

    public static ApplicationContext getApplicationContext()
    {
        return ms_context;
    }


    //Setter Injection
    @Override
    @Autowired //Normalde SpringBOOT "ApplicationContextAware" interface ini destkeleyen class' a otomatik
    //"setter injection" gerçekleştirir. Ama biz okunabilirlik olarak bunu "explicit" @Autowired olarak bildirdik.
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        ms_context = applicationContext;
    }
}
