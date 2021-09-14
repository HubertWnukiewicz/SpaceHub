package com.example.demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class BaseController {
    private final SessionFactory factory;
    private Session session;

    public BaseController() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public Session getSession() {
        return session;
    }

    public void startSession()
    {
        this.session = this.factory.openSession();
    }

    public void closeSession()
    {
        if (this.session.isOpen()) {
            this.session.close();
        }
    }

    public void startTransaction()
    {
        if (!this.session.getTransaction().isActive()) {
            this.session.beginTransaction();
        }
    }

    public void commitTransaction()
    {
        if (this.session.getTransaction().isActive()) {
            this.session.getTransaction().commit();
        }
    }
}
