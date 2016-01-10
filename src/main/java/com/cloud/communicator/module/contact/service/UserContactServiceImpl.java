package com.cloud.communicator.module.contact.service;

import com.cloud.communicator.module.contact.UserContact;
import com.cloud.communicator.module.contact.dao.UserContactDao;
import com.cloud.communicator.module.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("userContactService")
@Transactional(value = "transactionManagerPostgreSQL")
public class UserContactServiceImpl implements UserContactService {

    @Inject
    UserContactDao userContactDao;

    @Override
    public void saveUserContact(UserContact userContact) {
        userContactDao.saveUserContact(userContact);
    }

    @Override
    public void updateUserContact(UserContact userContact) {
        userContactDao.updateUserContact(userContact);
    }

    @Override
    public void deleteUserContact(UserContact userContact) {
        userContactDao.deleteUserContact(userContact);
    }

    @Override
    public void deleteUserContact(Integer userId, Integer personInBookId) {
        userContactDao.deleteUserContact(userId, personInBookId);
    }

    @Override
    public void addUsersToAddressBook(User owner, List<User> userContactList) {

        for(User userInBook : userContactList) {
            UserContact userContact = new UserContact();
            userContact.setUser(owner);
            userContact.setPersonInBook(userInBook);

            this.saveUserContact(userContact);
        }

    }

    @Override
    public List<UserContact> findContactsByUserId(Integer userId) {
        return userContactDao.findContactsByUserId(userId);
    }

}
