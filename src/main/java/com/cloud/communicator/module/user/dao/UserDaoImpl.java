package com.cloud.communicator.module.user.dao;


import com.cloud.communicator.AbstractDao;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * Created by Mateusz Brycki on 02/05/2015.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Inject
    private UserRoleService userRoleService;

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

    @Override
    public User findUserById(Integer userId) {
        Query query = getSession().createSQLQuery("SELECT * FROM user_account u WHERE u.user_id = :id");
        query.setString("id", userId.toString());

        return this.mapUserObject((Object[])query.uniqueResult());

    }

    private User mapUserObject(Object[] userObject) {

        User user = new User();
        user.setId((Integer)userObject[0]);
        user.setUsername((String) userObject[1]);
        user.setRole(this.userRoleService.findById((Integer) userObject[2]));
        user.setMail((String)userObject[3]);
        user.setIsActive((Boolean)userObject[5]);
        return user;
    }

}
