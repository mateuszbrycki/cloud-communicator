package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.UserMessageFolder;
import com.cloud.communicator.module.message.dao.UserMessageFolderDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("userMessageFolderService")
@Transactional(value = "transactionManagerPostgreSQL")
public class UserMessageFolderServiceImpl implements UserMessageFolderService{

    @Inject
    UserMessageFolderDao userMessageFolderDao;

    @Override
    public void saveUserMessageFolder(UserMessageFolder userMessageFolder) {
        this.userMessageFolderDao.saveUserMessageFolder(userMessageFolder);
    }

    @Override
    public void updateUserMessageFolder(UserMessageFolder userMessageFolder) {
        this.userMessageFolderDao.updateUserMessageFolder(userMessageFolder);
    }

    @Override
    public void deleteUserMessageFolder(UserMessageFolder userMessageFolder) {
        this.userMessageFolderDao.deleteUserMessageFolder(userMessageFolder);
    }

    @Override
    public UserMessageFolder getUserMessageFolder(Integer messageId, Integer userId){

       return this.userMessageFolderDao.getUserMessageFolder(messageId, userId);
    }
}
