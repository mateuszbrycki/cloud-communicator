package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.MessageReceiver;

import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */


public interface MessageReceiverService {

    void saveMessageReceiver(MessageReceiver messageReceiver);

    void updateMessageReceiver(MessageReceiver messageReceiver);

    void deleteMessageReceiver(MessageReceiver messageReceiver);

    MessageReceiver findMessageReceiver(Integer messageId, Integer userId);

    List<MessageReceiver> findMessageReceivers(Integer messageId);

    void changeMessageReadStatus(Integer messageId, Integer userId);

    void deleteMessageForUser(Integer messageId, Integer userId);

    void setMessageAsRead(Integer messageId, Integer userId);
}
