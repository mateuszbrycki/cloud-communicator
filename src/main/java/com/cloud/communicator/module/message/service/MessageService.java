package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.user.User;

import java.util.List;

/**
 * Created by Mateusz on 04.12.2015.
 */
public interface MessageService {

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Message message);

    void deleteMessage(Integer id);

    List<Message> findUserInboxMessages(Integer userId);

    List<Message> findUserFolderMessages(Integer userId, Integer folderId);

    List<Message> findUserMessagesByPhrase(Integer userId, String phrase);

    Message findMessageById(Integer messageId);

    Boolean isAllowedToSeeMessage(Message message, Integer userID);

    void sendMessage(Message message, List<User> receivers);

}
