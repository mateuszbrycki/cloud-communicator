package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.module.message.Folder;

import java.util.List;

public interface FolderDao {
    void saveFolder(Folder folder);

    void updateFolder(Folder folder);

    void deleteFolder(Folder folder);

    List<Folder> findUserFoldersByUserId(Integer userId);

    Folder findUserDefaultFolder(Integer userId);
}
