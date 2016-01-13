package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.message.UserMessageFolder;
import org.hibernate.Query;
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

    public UserMessageFolder getUserMessageFolder(Integer messageId, Integer userId){

        Query query = getSession().createSQLQuery(
                "SELECT umf.* FROM user_message_folder umf " +
                        "WHERE umf.fk_message_id = :messageId " +
                        "AND umf.fk_user_id = :userId");
        query.setInteger("messageId", messageId);
        query.setInteger("userId", userId);

        return this.mapUserMessageFolderObject((Object[]) query.uniqueResult());
    }


    private UserMessageFolder mapUserMessageFolderObject(Object[] userMessageFolderObject) {

        UserMessageFolder userMessageFolder = new UserMessageFolder();

        if (userMessageFolderObject == null) {
            return null;
        }

        userMessageFolder.setMessageId((Integer) userMessageFolderObject[0]);
        userMessageFolder.setUserId((Integer) userMessageFolderObject[1]);
        userMessageFolder.setFolderId((Integer) userMessageFolderObject[2]);

        return userMessageFolder;
    }
}
