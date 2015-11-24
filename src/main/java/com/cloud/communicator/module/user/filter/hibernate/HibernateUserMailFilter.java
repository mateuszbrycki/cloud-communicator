package com.cloud.communicator.module.user.filter.hibernate;

/**
 * Created by Mateusz on 28.09.2015.
 */


import com.cloud.communicator.filter.hibernate.HibernateFilter;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Mateusz on 28.09.2015.
 */
public class HibernateUserMailFilter implements HibernateFilter {

    private static final String PROPERTY_NAME = "mail";
    private String value = "";

    public HibernateUserMailFilter(String userMail) {
        this.value = userMail;
    }

    public Criterion execute() {
        return Restrictions.eq(PROPERTY_NAME, this.value);
    }
}
