package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDao;
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
public class MessageReceiverDaoImpl extends AbstractDao implements MessageReceiverDao {

    @Inject
    UserService userService;

    @Override
    public List<MessageReceiver> findMessageReceivers(Integer messageId) {
        Query query = getSession().createSQLQuery(
                "SELECT mr.* FROM message_receiver mr WHERE mr.fk_message_id = :id"
        );
        query.setString("id", messageId.toString());

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
        query.setString("messageId", messageId.toString());
        query.setString("userId", userId.toString());

        query.executeUpdate();
    }

    private MessageReceiver mapMessageReceiverObject(Object[] messageReceiverObject) {

        MessageReceiver messageReceiver = new MessageReceiver();

        messageReceiver.setReceiver(userService.findUserById((Integer) messageReceiverObject[1]));
        messageReceiver.setIsRead((Boolean) messageReceiverObject[2]);
        messageReceiver.setReadDate((Date)messageReceiverObject[3]);

        return messageReceiver;

    }

}
