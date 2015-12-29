package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.module.message.Message;

import java.util.List;

public interface MessageDao {

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Message message);

    void deleteMessage(Integer id);

    List<Message> findUserInboxMessages(Integer userId);

    List<Message> findUserFolderMessages(Integer userId, Integer folderId);

    Message findMessageById(Integer messageId);
}
