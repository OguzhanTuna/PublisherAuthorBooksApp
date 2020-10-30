package com.bwcompany.publisherauthorbookapp.provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionFactoryProvider {
    private static Optional<SessionFactory> ms_session;
    //...

    private static Optional<SessionFactory> createSessionFactory()
    {
        SessionFactory sessionFactory = null;

        try{
            Configuration configuration = new Configuration().configure();

            sessionFactory = configuration.buildSessionFactory();

            System.out.println("SessionFactory created successfully");
        }catch (Throwable ex){
            System.out.println("Error occurred while during SessionFactory create");
        }

        return Optional.ofNullable(sessionFactory);
    }

    //...

    public static Optional<SessionFactory> getSessionFactory()
    {
        return ms_session;
    }

    public static void closeSession()
    {
        ms_session.ifPresentOrElse(sf -> sf.getCurrentSession().close(), () -> System.out.println("No current session."));
    }

    @Bean
    private ApplicationRunner getRunnerForSession()
    {
        return args -> ms_session = createSessionFactory();
    }
}
