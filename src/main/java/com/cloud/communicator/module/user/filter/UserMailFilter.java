package com.cloud.communicator.module.user.filter;


import com.cloud.communicator.filter.Filter;
import com.cloud.communicator.filter.hibernate.HibernateFilter;
import com.cloud.communicator.module.user.filter.hibernate.HibernateUserMailFilter;

/**
 * Created by Mateusz on 28.09.2015.
 */
public class UserMailFilter implements Filter {
    private String value;

    public UserMailFilter(String userMail) {
        this.value = userMail;
    }

    public HibernateFilter getFilterForHibernate() {
        return new HibernateUserMailFilter(this.value);
    }
}
