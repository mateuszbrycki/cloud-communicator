package com.cloud.communicator.module.folder.service;

import com.cloud.communicator.module.folder.Folder;
import com.cloud.communicator.module.folder.dao.FolderDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("folderService")
@Transactional(value = "transactionManagerPostgreSQL")
public class FolderServiceImpl implements FolderService {

    @Inject
    FolderDao folderDao;

    @Override
    public void saveFolder(Folder folder) { this.folderDao.saveFolder(folder); }

    @Override
    public void updateFolder(Folder folder) { this.folderDao.updateFolder(folder); }

    @Override
    public void deleteFolder(Folder folder) { this.folderDao.deleteFolder(folder); }

    @Override
    public void deleteFolder(Integer id) { this.folderDao.deleteFolder(id); }

    @Override
    public void deleteFolder(Integer folderId, Integer userId) {this.folderDao.deleteFolder(folderId, userId); }

    @Override
    public void deleteUserFoldersByUserId(Integer userId) {
        this.folderDao.deleteUserFoldersByUserId(userId);
    }

    @Override
    public List<Folder> findUserFoldersByUserId(Integer userId){
        return this.folderDao.findUserFoldersByUserId(userId);
    }

    @Override
    public Folder findFolderById(Integer folderId) {
        return this.folderDao.findFolderById(folderId);
    }

    @Override
    public Folder findFolderById(Integer folderId, Integer userId) {
        return this.folderDao.findFolderById(folderId, userId);
    }

    @Override
    public Folder findUserDefaultFolder(Integer userId) {
        return this.folderDao.findUserDefaultFolder(userId);
    }
}
