package com.cloud.communicator.module.contact.dao;

import com.cloud.communicator.module.contact.UserContact;

import java.util.List;

public interface UserContactDao {
    void saveUserContact(UserContact userContact);

    void updateUserContact(UserContact userContact);

    void deleteUserContact(Integer userId, Integer personInBookId);

    void deleteUserContact(UserContact userContact);

    List<UserContact> findContactsByUserId(Integer userId);
}
