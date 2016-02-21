package com.cloud.communicator.module.message.factory;

import com.cloud.communicator.translator.context.AbstractContext;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.dto.MessageDTO;
import com.cloud.communicator.module.user.User;

import java.util.Date;

/**
 * Created by Mateusz on 21.02.2016.
 */
public class DTOContext implements AbstractContext<Message> {

    private MessageDTO messageDTO;
    private User author;

    public DTOContext(MessageDTO messageDTO, User author) {
        this.messageDTO = messageDTO;
        this.author = author;
    }

    public Message createFromContext() {
        Message message = new Message();

        message.setAuthor(author);
        message.setTopic(messageDTO.getTopic());
        message.setText(messageDTO.getText());
        message.setSendDate(new Date());

        return message;
    }
}
