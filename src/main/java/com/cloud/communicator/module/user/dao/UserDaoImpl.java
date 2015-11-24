package com.cloud.communicator.module.user.dao;


import com.cloud.communicator.filter.hibernate.HibernatePrepareFilters;
import org.hibernate.Criteria;
import com.cloud.communicator.AbstractDao;
import com.cloud.communicator.filter.FilterManager;
import com.cloud.communicator.module.user.User;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Mateusz Brycki on 02/05/2015.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void updateUser(User user) { update(user); }

    @Override
    public void deleteUserById(Integer id) {
        Query query = getSession().createSQLQuery("DELETE u.* FROM user u WHERE u.id = :id");
        query.setString("id", id.toString());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> find(FilterManager filterManager) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria = HibernatePrepareFilters.prepareCriteria(criteria, filterManager);

        List<User> users = criteria.list();

        return users;
    }


}
