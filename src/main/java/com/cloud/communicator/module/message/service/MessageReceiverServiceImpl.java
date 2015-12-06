package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.MessageReceiver;
import com.cloud.communicator.module.message.dao.MessageReceiverDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */
@Service("messageReceiverService")
@Transactional
public class MessageReceiverServiceImpl implements MessageReceiverService{

    @Inject
    MessageReceiverDao messageReceiverDao;

    @Override
    public List<MessageReceiver> findMessageReceivers(Integer messageId) {
        return this.messageReceiverDao.findMessageReceivers(messageId);
    }

    @Override
    public void changeMessageReadStatus(Integer messageId, Integer userId) {
        this.messageReceiverDao.changeMessageReadStatus(messageId, userId);
    }
}
