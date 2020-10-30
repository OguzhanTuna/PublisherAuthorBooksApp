package com.bwcompany.publisherauthorbookapp.test;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class AppComponent {
    //...

    public AppComponent()
    {
        System.out.println("AppComponent bean is created");
    }

    @Bean
    public ApplicationRunner getRunner()
    {
        return args -> System.out.println("OĞUZHAN");
    }
}
