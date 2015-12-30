package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.user.User;

import java.util.List;

public interface FolderService {

    void saveFolder(Folder folder);

    void updateFolder(Folder folder);

    void deleteFolder(Folder folder);

    void deleteFolder(Integer id);

    void deleteFolder(Integer folderId, Integer userId);

    void deleteUserFoldersByUserId(Integer userId);

    List<Folder> findUserFoldersByUserId(Integer userId);

    Folder findFolderById(Integer folderId);

    Folder findFolderById(Integer folderId, Integer userId);

    Folder findUserDefaultFolder(Integer userId);
}
