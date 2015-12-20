package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.message.dao.FolderDao;
import com.cloud.communicator.module.user.User;
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
    public List<Folder> findUserFoldersByUserId(Integer userId){
        return this.folderDao.findUserFoldersByUserId(userId);
    }

    @Override
    public Folder findUserDefaultFolder(Integer userId) {
        return this.folderDao.findUserDefaultFolder(userId);
    }
}
