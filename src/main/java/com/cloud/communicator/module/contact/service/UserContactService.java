package com.cloud.communicator.module.contact.service;

import com.cloud.communicator.module.contact.UserContact;
import com.cloud.communicator.module.user.User;

import java.util.List;

public interface UserContactService {
    void saveUserContact(UserContact userContact);

    void updateUserContact(UserContact userContact);

    void deleteUserContact(UserContact userContact);

    void deleteUserContact(Integer userId, Integer personInBookId);

    void addUsersToAddressBook(User owner, List<User> userContactList);

    List<UserContact> findContactsByUserId(Integer userId);
}
