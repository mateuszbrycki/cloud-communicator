package com.cloud.communicator.module.message.factory;

import com.cloud.communicator.module.message.MessageAbstractFactory;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.dto.MessageDTO;
import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;

import javax.inject.Inject;
import java.util.Date;

public class MessageFactory implements MessageAbstractFactory {

    @Inject
    private UserService userService;

    @Inject
    private MessageReceiverService messageReceiverService;

    @Override
    public Message createFromDTO(MessageDTO messageDTO, User author) {
        Message message = new Message();

        message.setAuthor(author);
        message.setTopic(messageDTO.getTopic());
        message.setText(messageDTO.getText());
        message.setSendDate(new Date());

        return message;
    }
    @Override
    public Message createFromObject(Object[] messageObject) {
        Message message = new Message();

        message.setId((Integer) messageObject[0]);
        message.setAuthor(this.userService.findUserById((Integer) messageObject[1]));
        message.setTopic((String) messageObject[2]);
        message.setText((String) messageObject[3]);
        message.setSendDate((Date) messageObject[4]);

        if(messageObject.length > 5) {
            message.setIsRead((Boolean) messageObject[5]);
        }

        message.setReceivers(
                this.messageReceiverService.findMessageReceivers(message.getId())
        );

        return message;
    }
}
