package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.dao.MessageDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Mateusz on 04.12.2015.
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageDao messageDao;


    @Override
    public void saveMessage(Message message) { messageDao.saveMessage(message); }

    @Override
    public void updateMessage(Message message) { messageDao.updateMessage(message); }

    @Override
    public void deleteMessage(Integer id) { messageDao.deleteMessage(id); }

    @Override
    public List<Message> findUserInboxMessages(Integer userId) {
        return messageDao.findUserInboxMessages(userId);
    }

    @Override
    public Message findMessageById(Integer messageId) {
        return this.messageDao.findMessageById(messageId);
    }
}
