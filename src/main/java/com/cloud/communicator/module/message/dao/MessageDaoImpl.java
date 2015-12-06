package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDao;
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
public class MessageDaoImpl extends AbstractDao implements MessageDao {

    @Inject
    UserService userService;

    @Inject
    MessageReceiverService messageReceiverService;

    @Override
    public void saveMessage(Message message) { persist(message);}

    @Override
    public void updateMessage(Message message) { update(message);}

    @Override
    public void deleteMessage(Integer id) {
        Query query = getSession().createSQLQuery("DELETE m.* FROM message m WHERE m.message_id = :id");
        query.setString("id", id.toString());
        query.executeUpdate();
    }

    @Override
    public List<Message> findUserInboxMessages(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT m.message_id, m.fk_author_id, m.topic, m.text, m.audit_cd, mr.is_read " +
                        "FROM message m " +
                        "JOIN message_receiver mr ON m.message_id = mr.fk_message_id " +
                        "WHERE mr.fk_user_id = :id");
        query.setString("id", userId.toString());

        List<Message> messages = new ArrayList<>();

        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
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
        query.setString("id", messageId.toString());

        return this.mapMessageObject((Object[]) query.uniqueResult());
    }

    private Message mapMessageObject(Object[] messageObject) {

        Message message = new Message();

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
