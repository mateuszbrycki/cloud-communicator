package com.cloud.communicator.module.message.service;

import com.cloud.communicator.config.AppConfig;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.MessageReceiver;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class MessageReceiverServiceTests {

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

    Message testMessage, testMessage2;
    User testUser, testUser2;

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

        testUser2 = new User();
        testUser2.setMail("tes2t@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        testMessage2 = new Message();
        testMessage2.setAuthor(testUser2);
        testMessage2.setTopic("Test message2 topic.");
        testMessage2.setText("Test message2.");
        testMessage2.setSendDate(new Date());
        messageService.sendMessage(testMessage2, Arrays.asList(testUser));
    }

    @Test
    public void saveMessageReceiver() {
        List<MessageReceiver> messageReceivers = messageReceiverService.findMessageReceivers(testMessage2.getId());
        MessageReceiver messageReceiver = messageReceivers.get(0);

        assertEquals((Integer) testUser.getId(), (Integer) messageReceiver.getReceiverId());
    }

    @Test
    public void updateMessageReceiver() {

        messageReceiverService.setMessageAsRead(testMessage2.getId(), testUser.getId());

        MessageReceiver messageReceiverUpdated = messageReceiverService.findMessageReceiver(testMessage2.getId(), testUser.getId());

        assertTrue(messageReceiverUpdated.getIsRead());
    }

    @Test
    public void deleteMessageReceiver() {

        messageReceiverService.deleteMessageForUser(testMessage2.getId(), testUser.getId());

        MessageReceiver deletedMessageReceiver = messageReceiverService.findMessageReceiver(testMessage2.getId(), testUser.getId());

        assertNull(deletedMessageReceiver);
    }

    @Test
    public void findMessageReceiver() {

        User testUser3 = new User();
        testUser3.setMail("tes3t@test.gmail.com");
        testUser3.setUsername("testusername3");
        testUser3.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser3.setPassword("testpassword3");
        userService.registerUser(testUser3);

        Message testMessage3 = new Message();
        testMessage3.setAuthor(testUser3);
        testMessage3.setTopic("Test message3 topic.");
        testMessage3.setText("Test message3.");
        testMessage3.setSendDate(new Date());
        messageService.sendMessage(testMessage3, Arrays.asList(testUser3));

        MessageReceiver foundReceiver = messageReceiverService.findMessageReceiver(testMessage3.getId(), testUser3.getId());

        assertEquals((Integer) testUser3.getId(), (Integer) foundReceiver.getReceiverId());

        
        userService.deleteUserById(testUser3.getId());
        messageService.deleteMessage(testMessage3);
    }

    @After
    public void clearDatabase() {
        userService.deleteUserById(testUser.getId());
        messageService.deleteMessage(testMessage.getId());
        messageService.deleteMessage(testMessage2.getId());
        userService.deleteUserById(testUser2.getId());
    }



}
