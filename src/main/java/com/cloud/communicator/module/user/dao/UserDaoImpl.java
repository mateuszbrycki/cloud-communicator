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
        Query query = getSession().createSQLQuery("DELETE u.* FROM user_account u WHERE u.user_id = :id");
        query.setString("id", id.toString());
        query.executeUpdate();
    }

    @Override
    public Boolean checkIfUserWithMailExists(String mail) {
        Query query = getSession().createSQLQuery("SELECT count(*) FROM user_account u WHERE u.mail = :mail");
        query.setString("mail", mail);

        //TODO mbrycki pokara mnie za to
        if(((Number)query.uniqueResult()).longValue() != 0) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkIfUserWithUsernameExists(String username) {
        Query query = getSession().createSQLQuery("SELECT count(*) FROM user_account u WHERE u.username = :username");
        query.setString("username", username);

        //TODO mbrycki pokara mnie za to
        if(((Number)query.uniqueResult()).longValue() != 0) {
            return true;
        }

        return false;
    }
}
