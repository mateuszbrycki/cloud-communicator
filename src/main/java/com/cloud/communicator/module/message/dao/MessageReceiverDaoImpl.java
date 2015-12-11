package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDaoPostgreSQL;
import com.cloud.communicator.module.message.MessageReceiver;
import com.cloud.communicator.module.user.service.UserService;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz on 05.12.2015.
 */
@Repository("messageReceiverDao")
public class MessageReceiverDaoImpl extends AbstractDaoPostgreSQL implements MessageReceiverDao {

    @Inject
    UserService userService;

    @Override
    public void saveMessageReceiver(MessageReceiver messageReceiver) { persist(messageReceiver);}

    @Override
    public void updateMessageReceiver(MessageReceiver messageReceiver) { update(messageReceiver);}

    @Override
    public void deleteMessageReceiver(Integer id) {
        Query query = getSession().createSQLQuery("DELETE m.* FROM message m WHERE m.message_id = :id");
        query.setString("id", id.toString());
        query.executeUpdate();
    }

    @Override
    public List<MessageReceiver> findMessageReceivers(Integer messageId) {
        Query query = getSession().createSQLQuery(
                "SELECT mr.* FROM message_receiver mr WHERE mr.fk_message_id = :id"
        );
        query.setInteger("id", messageId);

        List<MessageReceiver> receivers = new ArrayList<>();

        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
            receivers.add(this.mapMessageReceiverObject(row));
        }

        return receivers;
    }

    @Override
    public void changeMessageReadStatus(Integer messageId, Integer userId) {
        Query query = getSession().createSQLQuery(
                "UPDATE message_receiver " +
                        "SET is_read = NOT is_read " +
                        "WHERE fk_message_id = :messageId AND fk_user_id = :userId"
        );
        query.setInteger("messageId", messageId);
        query.setInteger("userId", userId);

        query.executeUpdate();
    }

    @Override
    public void deleteMessageForUser(Integer messageId, Integer userId) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM message_receiver " +
                        "WHERE fk_message_id = :messageId AND fk_user_id = :userId"
        );
        query.setInteger("messageId", messageId);
        query.setInteger("userId", userId);

        query.executeUpdate();
    }

    private MessageReceiver mapMessageReceiverObject(Object[] messageReceiverObject) {

        MessageReceiver messageReceiver = new MessageReceiver();

        messageReceiver.setReceiverId((Integer) messageReceiverObject[1]);
        messageReceiver.setIsRead((Boolean) messageReceiverObject[2]);
        messageReceiver.setReadDate((Date)messageReceiverObject[3]);

        return messageReceiver;

    }

}
