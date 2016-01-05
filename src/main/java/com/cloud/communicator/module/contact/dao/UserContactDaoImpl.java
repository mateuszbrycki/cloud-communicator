package com.cloud.communicator.module.contact.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.contact.UserContact;
import org.springframework.stereotype.Repository;

@Repository("userContactDao")
public class UserContactDaoImpl extends AbstractDaoPostgreSQL implements UserContactDao {

    @Override
    public void saveUserContact(UserContact userContact) {
        persist(userContact);
    }

    @Override
    public void updateUserContact(UserContact userContact) {
        update(userContact);
    }

    @Override
    public void deleteUserContact(UserContact userContact) {
        delete(userContact);
    }


}
