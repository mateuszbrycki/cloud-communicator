package com.cloud.communicator.module.userrole.dao;


import com.cloud.communicator.module.userrole.UserRole;

import java.util.Set;

/**
 * Created by Mateusz Brycki on 02/05/2015.
 */
public interface UserRoleDao {

    void saveUserRole(UserRole userRole);

    UserRole findById(Integer id);

    UserRole findByName(String role);

    Set<UserRole> findByUserId(Integer userId);
}

