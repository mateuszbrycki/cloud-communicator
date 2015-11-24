package com.cloud.communicator.filter;

import com.cloud.communicator.filter.hibernate.HibernateFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 28.09.2015.
 */
public class FilterManager {

    private List<Filter> filters = new ArrayList<Filter>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public List<HibernateFilter> getFiltersForHibernate() {

        List<HibernateFilter> hibernateFilters = new ArrayList<HibernateFilter>();

        for(Filter filter : filters) {
            hibernateFilters.add(filter.getFilterForHibernate());
        }

        return hibernateFilters;

    }

    public void resetFilters() {
        this.filters.clear();
    }
}
