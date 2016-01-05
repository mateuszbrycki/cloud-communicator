package com.cloud.communicator.module.contact.service;

import com.cloud.communicator.module.contact.UserContact;

public interface UserContactService {
    void saveUserContact(UserContact userContact);

    void updateUserContact(UserContact userContact);

    void deleteUserContact(UserContact userContact);
}
