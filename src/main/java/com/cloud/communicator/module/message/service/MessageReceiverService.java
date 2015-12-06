package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.MessageReceiver;

import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */


public interface MessageReceiverService {
    List<MessageReceiver> findMessageReceivers(Integer messageId);

    void changeMessageReadStatus(Integer messageId, Integer userId);
}
