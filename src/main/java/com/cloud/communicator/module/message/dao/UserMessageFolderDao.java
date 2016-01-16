package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.module.message.UserMessageFolder;

public interface UserMessageFolderDao {
    void saveUserMessageFolder(UserMessageFolder userMessageFolder);

    void updateUserMessageFolder(UserMessageFolder userMessageFolder);

    void updateUserMessageFolder(Integer messageId, Integer userId, Integer folderId);

    void deleteUserMessageFolder(UserMessageFolder userMessageFolder);

    UserMessageFolder getUserMessageFolder(Integer messageId, Integer userId);
}
