package com.cloud.communicator.module.folder.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.folder.Folder;
import com.cloud.communicator.module.folder.FolderAbstractFactory;
import com.cloud.communicator.module.folder.factory.FolderFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository("folderDao")
public class FolderDaoImpl  extends AbstractDaoPostgreSQL implements FolderDao {

    @Inject
    private FolderAbstractFactory folderFactory;

    @Override
    public void saveFolder(Folder folder) { persist(folder); }

    @Override
    public void updateFolder(Folder folder) { update(folder); }

    @Override
    public void deleteFolder(Folder folder) { delete(folder); }

    @Override
    public void deleteFolder(Integer id) {
        Query query = getSession().createSQLQuery("DELETE FROM folder f " +
                "WHERE f.folder_id = :id AND f.is_default_user_folder != true");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteFolder(Integer folderId, Integer userId) {
        Query query = getSession().createSQLQuery("DELETE FROM folder f " +
                "WHERE f.folder_id = :folderId " +
                "AND f.fk_owner_id = :userId AND f.is_default_user_folder != true");
        query.setInteger("folderId", folderId);
        query.setInteger("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void deleteUserFoldersByUserId(Integer userId) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM folder f " +
                        "WHERE f.fk_owner_id = :userId "
        );

        query.setInteger("userId", userId);

        query.executeUpdate();
    }

    @Override
    public List<Folder> findUserFoldersByUserId(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT f.* " +
                        "FROM folder_view f " +
                        "WHERE f.fk_owner_id = :id " +
                        "ORDER BY f.folder_id");
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
                "SELECT f.* " +
                        "FROM folder_view f " +
                        "WHERE f.fk_owner_id= :id AND f.is_default_user_folder = true LIMIT 1");
        query.setInteger("id", userId);

        return this.mapFolderObject((Object[]) query.uniqueResult());
    }

    @Override
    public Folder findFolderById(Integer folderId) {

        Query query = getSession().createSQLQuery(
                "SELECT f.* " +
                        "FROM folder_view f " +
                        "WHERE f.folder_id= :id");
        query.setInteger("id", folderId);

        return this.mapFolderObject((Object[]) query.uniqueResult());
    }

    @Override
    public Folder findFolderById(Integer folderId, Integer userId) {

        Query query = getSession().createSQLQuery(
                "SELECT f.* " +
                        "FROM folder_view f " +
                        "WHERE f.folder_id= :id AND " +
                        "f.fk_owner_id = :userId");
        query.setInteger("id", folderId);
        query.setInteger("userId", userId);

        return this.mapFolderObject((Object[]) query.uniqueResult());
    }

    private Folder mapFolderObject(Object[] folderObject) {

        if(folderObject == null) {
            return null;
        }

        Folder folder = folderFactory.createFromObject(folderObject);
        
        return folder;
    }
}
