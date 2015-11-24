package com.cloud.communicator.filter.hibernate;

import org.hibernate.criterion.Criterion;

/**
 * Created by Mateusz on 28.09.2015.
 */
public interface HibernateFilter {
    Criterion execute();
}
