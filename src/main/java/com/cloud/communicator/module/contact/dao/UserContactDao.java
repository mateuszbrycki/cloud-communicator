package com.cloud.communicator.module.contact.dao;

import com.cloud.communicator.module.contact.UserContact;

public interface UserContactDao {
    void saveUserContact(UserContact userContact);

    void updateUserContact(UserContact userContact);

    void deleteUserContact(UserContact userContact);
}
