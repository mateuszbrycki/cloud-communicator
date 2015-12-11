package com.cloud.communicator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;

public class AbstractDaoPostgreSQL {

    @Inject
    @Qualifier("sessionFactoryPostgres")
    private SessionFactory AbstractDaoPostgreSQL;

    protected Session getSession(){
        return AbstractDaoPostgreSQL.getCurrentSession();
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void update(Object entity) { getSession().update(entity);}

    public void delete(Object entity) {
        getSession().delete(entity);
    }


}
