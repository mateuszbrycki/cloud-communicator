package com.cloud.communicator.module.user.service;


import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mateusz Brycki on 28/04/2015.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public void saveUser(User user) { userDao.saveUser(user);}

    @Override
    public void updateUser(User user) { userDao.updateUser(user);}

    @Override
    public void deleteUserById(Integer id) { userDao.deleteUserById(id); }

    @Override
    public Boolean checkIfUserWithMailExists(String mail) {
        return userDao.checkIfUserWithMailExists(mail);
    }

    @Override
    public Boolean checkIfUserWithUsernameExists(String username) {
       return userDao.checkIfUserWithUsernameExists(username);
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }
}

