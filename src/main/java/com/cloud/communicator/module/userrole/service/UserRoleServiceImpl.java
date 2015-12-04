package com.cloud.communicator.module.userrole.service;

import com.cloud.communicator.module.userrole.UserRole;
import com.cloud.communicator.module.userrole.dao.UserRoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Inject
    private UserRoleDao userRoleDao;

    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleDao.saveUserRole(userRole);
    }

    @Override
    public UserRole findByName(String role) { return userRoleDao.findByName(role); }

    @Override
    public UserRole findById(Integer id) { return userRoleDao.findById(id); }

}

