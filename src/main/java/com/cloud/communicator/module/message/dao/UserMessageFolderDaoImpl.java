package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.message.UserMessageFolder;
import org.springframework.stereotype.Repository;

@Repository("userMessageFolderDao")
public class UserMessageFolderDaoImpl extends AbstractDaoPostgreSQL implements UserMessageFolderDao {

    @Override
    public void saveUserMessageFolder(UserMessageFolder userMessageFolder) {
        persist(userMessageFolder);
    }

    @Override
    public void updateUserMessageFolder(UserMessageFolder userMessageFolder) {
        update(userMessageFolder);
    }

    @Override
    public void deleteUserMessageFolder(UserMessageFolder userMessageFolder) {
        delete(userMessageFolder);
    }
}
