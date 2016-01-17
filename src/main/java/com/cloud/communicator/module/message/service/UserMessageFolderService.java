package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.UserMessageFolder;

public interface UserMessageFolderService {
    void saveUserMessageFolder(UserMessageFolder userMessageFolder);

    void updateUserMessageFolder(UserMessageFolder userMessageFolder);

    void updateUserMessageFolder(Integer messageId, Integer userId, Integer folderId);

    void deleteUserMessageFolder(UserMessageFolder userMessageFolder);

    public UserMessageFolder getUserMessageFolder(Integer messageId, Integer userId);
}
