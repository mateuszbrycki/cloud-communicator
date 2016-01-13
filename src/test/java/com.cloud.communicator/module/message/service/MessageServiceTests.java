package com.cloud.communicator.module.message.service;

import com.cloud.communicator.config.AppConfig;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.MessageReceiver;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class MessageServiceTests {

    @Inject
    MessageService messageService;

    @Inject
    MessageReceiverService messageReceiverService;

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    @Qualifier("sessionFactoryPostgres")
    SessionFactory sessionFactory;

    Message testMessage;
    User testUser;

    private static final Logger logger = Logger.getLogger(MessageServiceTests.class);

    @Before
    public void initObjects() {
        testUser = new User();
        testUser.setMail("test@test.gmail.com");
        testUser.setUsername("testusername");
        testUser.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword");
        userService.registerUser(testUser);

        testMessage = new Message();
        testMessage.setAuthor(testUser);
        testMessage.setTopic("Test message topic.");
        testMessage.setText("Test message.");
        testMessage.setSendDate(new Date());
        messageService.saveMessage(testMessage);
    }

    @Test
    public void saveMessage() {
        Message insertedMessage = messageService.findMessageById(testMessage.getId());

        assertNotNull(insertedMessage);
        assertEquals("Test message topic.", insertedMessage.getTopic());
        assertEquals("Test message.", insertedMessage.getText());
        assertEquals("testusername", insertedMessage.getAuthor().getUsername());

    }

    @Test
    public void updateMessage() {

        testMessage.setText("Test message after update.");

        messageService.updateMessage(testMessage);

        Message insertedMessage = messageService.findMessageById(testMessage.getId());

        assertNotNull(insertedMessage);
        assertEquals("Test message after update.", insertedMessage.getText());

    }

    @Test
    public void deleteMessage() {
        messageService.deleteMessage(testMessage);

        Message deletedMessage = messageService.findMessageById(testMessage.getId());

        assertNull(deletedMessage);
    }

    @Test
    public void deleteMessageById() {
        messageService.deleteMessage(testMessage.getId());

        Message deletedMessage = messageService.findMessageById(testMessage.getId());

        assertNull(deletedMessage);
    }

    @Test
    public void findUserInboxMessages() {
        User testUser2 = new User();
        testUser2.setMail("tes2t@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        Message testMessage2 = new Message();
        testMessage2.setAuthor(testUser2);
        testMessage2.setTopic("Test message2 topic.");
        testMessage2.setText("Test message2.");
        testMessage2.setSendDate(new Date());
        messageService.sendMessage(testMessage2, Arrays.asList(testUser));

        List<Message> inboxMessages = messageService.findUserInboxMessages(testUser.getId());

        assertEquals(testMessage2.getId(), inboxMessages.get(0).getId());
        assertEquals(testMessage2.getAuthor().getUsername(), testUser2.getUsername());

        messageService.deleteMessage(testMessage2.getId());
        userService.deleteUserById(testUser2.getId());

        inboxMessages = messageService.findUserInboxMessages(testUser.getId());

        assertEquals(0, inboxMessages.size());
    }

    @Test
    public void findUserInboxMessagesManyReceivers() {
        User testUser2 = new User();
        testUser2.setMail("tes2t@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        User testUser3 = new User();
        testUser3.setMail("test3@test.gmail.com");
        testUser3.setUsername("testusername3");
        testUser3.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser3.setPassword("testpassword3");
        userService.registerUser(testUser3);

        Message testMessage2 = new Message();
        testMessage2.setAuthor(testUser2);
        testMessage2.setTopic("Test message2 topic.");
        testMessage2.setText("Test message2.");
        testMessage2.setSendDate(new Date());
        messageService.sendMessage(testMessage2, Arrays.asList(testUser,testUser3));

        List<Message> inboxMessagesTestUser = messageService.findUserInboxMessages(testUser.getId());
        List<Message> inboxMessagesTestUser3 = messageService.findUserInboxMessages(testUser3.getId());

        assertEquals(1, inboxMessagesTestUser.size());
        assertEquals(1, inboxMessagesTestUser3.size());

        messageService.deleteMessage(testMessage2.getId());

        inboxMessagesTestUser = messageService.findUserInboxMessages(testUser.getId());
        inboxMessagesTestUser3 = messageService.findUserInboxMessages(testUser3.getId());

        assertEquals(0, inboxMessagesTestUser.size());
        assertEquals(0, inboxMessagesTestUser3.size());

        userService.deleteUserById(testUser2.getId());
        userService.deleteUserById(testUser3.getId());

    }

    @Test
    public void checkSearchingMessage() {
        User testUser2 = new User();
        testUser2.setMail("tes2t@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        Message testMessage2 = new Message();
        testMessage2.setAuthor(testUser);
        testMessage2.setTopic("Test message2 topic.");
        testMessage2.setText("Test message2.");
        testMessage2.setSendDate(new Date());
        messageService.sendMessage(testMessage2, Arrays.asList(testUser2));

        User testUser3 = new User();
        testUser3.setMail("test3@test.gmail.com");
        testUser3.setUsername("testusername3");
        testUser3.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser3.setPassword("testpassword3");
        userService.registerUser(testUser3);

        //test user as receiver
        List<Message> messages = messageService.findUserMessagesByPhrase(testUser2.getId(), "a");
        assertEquals(1, messages.size());

        //testuser3 is author nor receiver
        messages = messageService.findUserMessagesByPhrase(testUser3.getId(), "a");
        assertEquals(0, messages.size());

        Message testMessage3 = new Message();
        testMessage3.setAuthor(testUser);
        testMessage3.setTopic("Test message3 topic.");
        testMessage3.setText("Test message3.");
        testMessage3.setSendDate(new Date());
        messageService.sendMessage(testMessage3, Arrays.asList(testUser2, testUser3));

        //testuser as author
        messages = messageService.findUserMessagesByPhrase(testUser.getId(), "a");
        assertEquals(2, messages.size()); //message2 + message3 + message

        //testuser2 as receiver
        messages = messageService.findUserMessagesByPhrase(testUser2.getId(), "a");
        assertEquals(2, messages.size()); //message2 + message3

        //testuser3 is author nor receiver
        messages = messageService.findUserMessagesByPhrase(testUser3.getId(), "a");
        assertEquals(1, messages.size());

        userService.deleteUserById(testUser2.getId());
        userService.deleteUserById(testUser3.getId());
        messageService.deleteMessage(testMessage2);
        messageService.deleteMessage(testMessage3);
    }

    @Test
    public void checkIfUserIsAllowedToSeeMessage() {

        User testUser2 = new User();
        testUser2.setMail("tes2t@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        Message testMessage2 = new Message();
        testMessage2.setAuthor(testUser2);
        testMessage2.setTopic("Test message2 topic.");
        testMessage2.setText("Test message2.");
        testMessage2.setSendDate(new Date());
        messageService.sendMessage(testMessage2, Arrays.asList(testUser));

        Message messageWithReceivers = messageService.findMessageById(testMessage2.getId());

        assertTrue(messageService.isAllowedToSeeMessage(messageWithReceivers, testUser.getId()));
        assertFalse(messageService.isAllowedToSeeMessage(messageWithReceivers, testUser2.getId()));

        messageService.deleteMessage(testMessage2.getId());
        userService.deleteUserById(testUser2.getId());
    }

    @After
    public void clearDatabase() {
        userService.deleteUserById(testUser.getId());
        messageService.deleteMessage(testMessage.getId());
    }

}
