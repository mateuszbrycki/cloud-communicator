package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.user.User;

import java.util.List;

public interface FolderService {

    void saveFolder(Folder folder);

    void updateFolder(Folder folder);

    void deleteFolder(Folder folder);

    List<Folder> findUserFoldersByUserId(Integer userId);

    Folder findUserDefaultFolder(Integer userId);
}
