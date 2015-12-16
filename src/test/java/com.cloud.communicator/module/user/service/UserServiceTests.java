package com.cloud.communicator.module.user.service;

import com.cloud.communicator.config.AppConfig;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.userrole.service.UserRoleService;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class UserServiceTests {

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    @Qualifier("sessionFactoryMySQL")
    SessionFactory sessionFactory;

    User testUser;

    private static final Logger logger = Logger.getLogger(UserServiceTests.class);

    @Before
    public void initObjects() {
        testUser = new User();
        testUser.setMail("test@test.gmail.com");
        testUser.setUsername("testusername");
        testUser.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword");
        userService.saveUser(testUser);

    }

    @Test
    public void saveUser() {

        User newUser = userService.findUserById(testUser.getId());

        assertNotNull(newUser);
        assertEquals("test@test.gmail.com", newUser.getMail());
        assertEquals("testusername", newUser.getUsername());
        assertEquals(User.DEFAULT_ROLE,newUser.getRole());
        assertEquals("testpassword", newUser.getPassword());

    }

    @Test
    public void updateUser() {

        testUser.setPassword("newpassword");

        userService.updateUser(testUser);

        User newUser = userService.findUserById(testUser.getId());

        assertNotNull(newUser);
        assertEquals("newpassword", newUser.getPassword());

    }

    @Test
    public void deleteUserById() {
        userService.deleteUserById(testUser.getId());

        User deletedUser = userService.findUserById(testUser.getId());

        assertNull(deletedUser);
    }

    @Test
    public void checkIfUserWithMailExists() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.saveUser(testUser2);

        assertTrue(userService.checkIfUserWithMailExists("test2@test.gmail.com"));
        assertFalse(userService.checkIfUserWithMailExists("exampleusername"));

        userService.deleteUserById(testUser2.getId());
    }

    @After
    public void clearDatabase() {
        userService.deleteUserById(testUser.getId());

    }

}
