package com.cloud.communicator.module.user.dao;


import com.cloud.communicator.filter.FilterManager;
import com.cloud.communicator.module.user.User;

import java.util.List;

/**
 * Created by Mateusz Brycki on 02/05/2015.
 */
public interface UserDao {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    List<User> find(FilterManager filterManager);


}

