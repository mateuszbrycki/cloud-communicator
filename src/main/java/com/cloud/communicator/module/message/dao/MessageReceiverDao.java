package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.module.message.MessageReceiver;

import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */

public interface MessageReceiverDao {
    List<MessageReceiver> findMessageReceivers(Integer messageId);

    void changeMessageReadStatus(Integer messageId, Integer userId);
}
