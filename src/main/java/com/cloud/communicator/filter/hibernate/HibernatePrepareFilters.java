package com.cloud.communicator.filter.hibernate;


import org.hibernate.Criteria;
import com.cloud.communicator.filter.FilterManager;

import java.util.List;

/**
 * Created by Mateusz on 28.09.2015.
 */
public class HibernatePrepareFilters {

    public static Criteria prepareCriteria(Criteria criteria, FilterManager filterManager) {

        List<HibernateFilter> hibernateFilters = filterManager.getFiltersForHibernate();

        for(HibernateFilter filter : hibernateFilters) {
            criteria.add(filter.execute());
        }

        return criteria;
    }

}