package com.cloud.communicator.module.message.factory;

import com.cloud.communicator.translator.context.AbstractContext;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.user.service.UserService;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Mateusz on 21.02.2016.
 */
public class ObjectContext implements AbstractContext<Message> {

    /**
     * NullPointerException - services won't be injected
     */
    @Inject
    private UserService userService;

    @Inject
    private MessageReceiverService messageReceiverService;

    private Object[] messageObject;


    public ObjectContext(Object[] messageObject) {
        this.messageObject = messageObject;
    }

    @Override
    public Message createFromContext() {
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
