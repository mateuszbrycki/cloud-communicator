package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.MessageReceiver;
import com.cloud.communicator.module.message.dao.MessageReceiverDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */
@Service("messageReceiverService")
@Transactional(value = "transactionManagerPostgreSQL")
public class MessageReceiverServiceImpl implements MessageReceiverService{

    @Inject
    MessageReceiverDao messageReceiverDao;

    @Override
    public void saveMessageReceiver(MessageReceiver messageReceiver) { this.messageReceiverDao.saveMessageReceiver(messageReceiver); }

    @Override
    public void updateMessageReceiver(MessageReceiver messageReceiver) { this.messageReceiverDao.updateMessageReceiver(messageReceiver); }

    @Override
    public void deleteMessageReceiver(Integer id) { this.messageReceiverDao.deleteMessageReceiver(id); }

    @Override
    public List<MessageReceiver> findMessageReceivers(Integer messageId) {
        return this.messageReceiverDao.findMessageReceivers(messageId);
    }

    @Override
    public void changeMessageReadStatus(Integer messageId, Integer userId) {
        this.messageReceiverDao.changeMessageReadStatus(messageId, userId);
    }

    @Override
    public void deleteMessageForUser(Integer messageId, Integer userId) {
        this.messageReceiverDao.deleteMessageForUser(messageId, userId);
    }

    @Override
    public void setMessageAsRead(Integer messageId, Integer userId) {
        this.messageReceiverDao.setMessageAsRead(messageId, userId);
    }
}
