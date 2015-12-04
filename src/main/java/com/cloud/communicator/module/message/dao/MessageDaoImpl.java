package com.cloud.communicator.module.message.dao;

import com.cloud.communicator.AbstractDao;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.user.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import sun.misc.resources.Messages;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao implements MessageDao {

    @Inject
    UserService userService;

    @Override
    public void saveMessage(Message message) { persist(message);}

    @Override
    public void updateMessage(Message message) { update(message);}

    @Override
    public void deleteMessage(Integer id) {
        Query query = getSession().createSQLQuery("DELETE m.* FROM mesage m WHERE m.message_id = :id");
        query.setString("id", id.toString());
        query.executeUpdate();
    }

    @Override
    public List<Message> findUserInboxMessages(Integer userId) {
        Query query = getSession().createSQLQuery(
                "SELECT m.* " +
                        "FROM message m " +
                        "JOIN message_receiver mr ON m.message_id = mr.fk_message_id " +
                        "WHERE mr.fk_user_id = :id");
        query.setString("id", userId.toString());

        List<Message> messages = new ArrayList<Message>();

        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
            messages.add(this.mapMessageObject(row));
        }

        return messages;
    }

    private Message mapMessageObject(Object[] messageObject) {

        Message message = new Message();
        message.setId((Integer)messageObject[0]);
        message.setAuthor(this.userService.findUserById((Integer)messageObject[1]));
        message.setTopic((String) messageObject[2]);
        message.setText((String) messageObject[3]);
        return message;
    }
}
