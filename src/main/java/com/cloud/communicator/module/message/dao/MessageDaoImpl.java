package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.user.service.UserService;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl extends AbstractDaoPostgreSQL implements MessageDao {

    @Inject
    private UserService userService;

    @Inject
    private MessageReceiverService messageReceiverService;

    @Override
    public void saveMessage(Message message) {
        persist(message);
    }

    @Override
    public void updateMessage(Message message) {
        update(message);
    }

    @Override
    public void deleteMessage(Message message) {
        delete(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        Query query = getSession().createSQLQuery("DELETE FROM message m WHERE m.message_id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Message> findUserInboxMessages(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT m.message_id, m.fk_author_id, m.topic, m.text, m.audit_cd, mr.is_read " +
                        "FROM message m " +
                        "JOIN message_receiver mr ON m.message_id = mr.fk_message_id " +
                        "JOIN user_message_folder umf ON umf.fk_message_id = m.message_id AND umf.fk_user_id = mr.fk_user_id " +
                        "JOIN folder f ON umf.fk_folder_id = f.folder_id " +
                        "WHERE mr.fk_user_id = :userId AND f.is_default_user_folder = true " +
                        "ORDER BY m.audit_cd DESC");
        query.setInteger("userId", userId);

        List<Message> messages = new ArrayList<>();

        List<Object[]> rows = query.list();
        for (Object[] row : rows) {
            messages.add(this.mapMessageObject(row));
        }

        return messages;
    }

    @Override
    public List<Message> findUserFolderMessages(Integer userId, Integer folderId) {
        Query query = getSession().createSQLQuery(
                "SELECT m.message_id, m.fk_author_id, m.topic, m.text, m.audit_cd, mr.is_read " +
                        "FROM message m " +
                        "JOIN message_receiver mr ON m.message_id = mr.fk_message_id " +
                        "JOIN user_message_folder umf ON umf.fk_message_id = m.message_id " +
                        "JOIN folder f ON umf.fk_folder_id = f.folder_id " +
                        "WHERE mr.fk_user_id = :userId AND f.folder_id = :folderId " +
                        "ORDER BY m.audit_cd DESC");
        query.setInteger("userId", userId);
        query.setInteger("folderId", folderId);

        List<Message> messages = new ArrayList<>();

        List<Object[]> rows = query.list();
        for (Object[] row : rows) {
            messages.add(this.mapMessageObject(row));
        }

        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) {
        Query query = getSession().createSQLQuery(
                "SELECT m.* " +
                        "FROM message m " +
                        "WHERE m.message_id = :id LIMIT 1");
        query.setInteger("id", messageId);

        return this.mapMessageObject((Object[]) query.uniqueResult());
    }

    private Message mapMessageObject(Object[] messageObject) {

        Message message = new Message();

        if (messageObject == null) {
            return null;
        }

        message.setId((Integer) messageObject[0]);
        message.setAuthor(this.userService.findUserById((Integer) messageObject[1]));
        message.setTopic((String) messageObject[2]);
        message.setText((String) messageObject[3]);
        message.setSendDate((Date) messageObject[4]);
        message.setIsRead((Boolean) messageObject[5]);
        message.setReceivers(
                this.messageReceiverService.findMessageReceivers(message.getId())
        );

        return message;
    }
}
