package com.cloud.communicator.module.user.service;


import com.cloud.communicator.module.user.User;

/**
 * Created by Mateusz Brycki on 28/04/2015.
 */
public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    Boolean checkIfUserWithMailExists(String mail);

    Boolean checkIfUserWithUsernameExists(String username);
}
