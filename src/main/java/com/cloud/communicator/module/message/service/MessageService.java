package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Message;

import java.util.List;

/**
 * Created by Mateusz on 04.12.2015.
 */
public interface MessageService {

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Integer id);

    List<Message> findUserInboxMessages(Integer userId);
}
