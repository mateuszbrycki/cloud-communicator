package com.cloud.communicator.filter;


import com.cloud.communicator.filter.hibernate.HibernateFilter;

/**
 * Created by Mateusz on 28.09.2015.
 */
public interface Filter {

    HibernateFilter getFilterForHibernate();
}
