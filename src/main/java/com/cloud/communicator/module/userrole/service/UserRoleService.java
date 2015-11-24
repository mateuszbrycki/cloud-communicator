package com.cloud.communicator.module.userrole.service;


import com.cloud.communicator.module.userrole.UserRole;

/**
 * Created by Mateusz Brycki on 28/04/2015.
 */
public interface UserRoleService {

    void saveUserRole(UserRole userRole);

    UserRole findByName(String role);
}
