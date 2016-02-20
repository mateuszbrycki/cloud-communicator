package com.cloud.communicator.module.contact.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.contact.UserContact;
import com.cloud.communicator.module.user.service.UserService;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository("userContactDao")
public class UserContactDaoImpl extends AbstractDaoPostgreSQL implements UserContactDao {

    @Inject
    private UserService userService;

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

    @Override
    public void deleteUserContact(Integer userId, Integer personInBookId) {
        Query query = getSession().createSQLQuery(
                "DELETE " +
                        "FROM user_contacts uc " +
                        "WHERE uc.fk_user_id = :userId AND uc.fk_person_in_book_id = :personInBookId");
        query.setInteger("userId", userId);
        query.setInteger("personInBookId", personInBookId);

        query.executeUpdate();
    }

    @Override
    public List<UserContact> findContactsByUserId(Integer userId) {

        Query query = getSession().createSQLQuery(
                "SELECT uc.* " +
                        "FROM user_contacts uc " +
                        "WHERE uc.fk_user_id = :id");
        query.setInteger("id", userId);

        List<UserContact> contacts = new ArrayList<>();

        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
            contacts.add(this.mapUserContactObject(row));
        }

        return contacts;
    }

    private UserContact mapUserContactObject(Object[] userContactObject) {

        UserContact userContact = new UserContact();
        userContact.setUser(userService.findUserById((Integer) userContactObject[0]));
        userContact.setPersonInBook(userService.findUserById((Integer) userContactObject[1]));

        return userContact;
    }

}
