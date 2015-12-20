package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.user.service.UserService;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository("folderDao")
public class FolderDaoImpl  extends AbstractDaoPostgreSQL implements FolderDao {

    @Inject
    private UserService userService;

    @Override
    public void saveFolder(Folder folder) { persist(folder); }

    @Override
    public void updateFolder(Folder folder) { update(folder); }

    @Override
    public void deleteFolder(Folder folder) { delete(folder); }

    @Override
    public List<Folder> findUserFoldersByUserId(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT f.*, get_user_folder_unread_messages(f.fk_owner_id, f.folder_id) AS unread_message " +
                        "FROM folder f " +
                        "WHERE f.fk_owner_id = :id");
        query.setInteger("id", userId);

        List<Folder> folders = new ArrayList<>();

        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
            folders.add(this.mapFolderObject(row));
        }

        return folders;
    }

    @Override
    public Folder findUserDefaultFolder(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT f.*,  get_user_folder_unread_messages(f.fk_owner_id, f.folder_id) AS unread_message " +
                        "FROM folder f " +
                        "WHERE f.fk_owner_id= :id AND is_default_user_folder = true LIMIT 1");
        query.setInteger("id", userId);

        return this.mapFolderObject((Object[]) query.uniqueResult());
    }

    private Folder mapFolderObject(Object[] folderObject) {

        if(folderObject == null) {
            return null;
        }

        Folder folder = new Folder();
        folder.setId((Integer) folderObject[0]);
        folder.setName((String) folderObject[1]);
        folder.setDescription((String) folderObject[2]);
        folder.setLabelColor((Integer) folderObject[3]);
        folder.setOwner(this.userService.findUserById((Integer) folderObject[4]));
        folder.setIsUserDefaultFolder((Boolean) folderObject[5]);
        folder.setUnreadMessages((Integer) folderObject[8]);

        return folder;
    }
}
