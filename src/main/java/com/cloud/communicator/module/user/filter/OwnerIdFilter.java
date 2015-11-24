package com.cloud.communicator.module.user.filter;


import com.cloud.communicator.filter.Filter;
import com.cloud.communicator.filter.hibernate.HibernateFilter;
import com.cloud.communicator.module.user.filter.hibernate.HibernateOwnerIdFilter;

/**
 * Created by Mateusz on 28.09.2015.
 */
public class OwnerIdFilter implements Filter {
    private Integer value;

    public OwnerIdFilter(Integer userId) {
        this.value = userId;
    }


    public HibernateFilter getFilterForHibernate() {
        return new HibernateOwnerIdFilter(this.value);
    }
}
