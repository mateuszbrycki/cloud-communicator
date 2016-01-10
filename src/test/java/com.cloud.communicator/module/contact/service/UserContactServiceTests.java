package com.cloud.communicator.module.contact.service;

import com.cloud.communicator.config.AppConfig;
import com.cloud.communicator.module.contact.UserContact;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class UserContactServiceTests {

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    UserContactService userContactService;

    private User testUser1, testUser2, testUser3;

    @Before
    public void initObjects() {
        testUser1 = new User();
        testUser1.setMail("test@test.gmail.com");
        testUser1.setUsername("testusername");
        testUser1.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser1.setPassword("testpassword");
        userService.registerUser(testUser1);

        testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        testUser3 = new User();
        testUser3.setMail("test3@test.gmail.com");
        testUser3.setUsername("testusername3");
        testUser3.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser3.setPassword("testpassword3");
        userService.registerUser(testUser3);
    }

    @Test
    public void saveAndDeleteUserContact() {
        UserContact userContact = new UserContact();
        userContact.setUser(testUser1);
        userContact.setPersonInBook(testUser2);
        userContactService.saveUserContact(userContact);
        List<UserContact> contacts = userContactService.findContactsByUserId(testUser1.getId());

        assertEquals(1, contacts.size());

        UserContact userContact2 = new UserContact();
        userContact2.setUser(testUser1);
        userContact2.setPersonInBook(testUser3);
        userContactService.saveUserContact(userContact2);
        contacts = userContactService.findContactsByUserId(testUser1.getId());

        assertEquals(2, contacts.size());

        userContactService.deleteUserContact(userContact);
        contacts = userContactService.findContactsByUserId(testUser1.getId());
        assertEquals(1, contacts.size());

        userContactService.deleteUserContact(userContact2);
        contacts = userContactService.findContactsByUserId(testUser1.getId());
        assertEquals(0, contacts.size());
    }

    @Test
    public void deleteUserContact() {
        UserContact userContact = new UserContact();
        userContact.setUser(testUser1);
        userContact.setPersonInBook(testUser2);
        userContactService.saveUserContact(userContact);
        List<UserContact> contacts = userContactService.findContactsByUserId(testUser1.getId());

        assertEquals(1, contacts.size());

        UserContact userContact2 = new UserContact();
        userContact2.setUser(testUser1);
        userContact2.setPersonInBook(testUser3);
        userContactService.saveUserContact(userContact2);
        contacts = userContactService.findContactsByUserId(testUser1.getId());

        assertEquals(2, contacts.size());

        userContactService.deleteUserContact(testUser1.getId(), testUser2.getId());
        contacts = userContactService.findContactsByUserId(testUser1.getId());
        assertEquals(1 , contacts.size());

        userContactService.deleteUserContact(testUser1.getId(), testUser3.getId());
        contacts = userContactService.findContactsByUserId(testUser1.getId());
        assertEquals(0 , contacts.size());
    }

    @After
    public void clearDatabase() {
        userService.deleteUserById(testUser1.getId());
        userService.deleteUserById(testUser2.getId());
        userService.deleteUserById(testUser3.getId());
    }
}
