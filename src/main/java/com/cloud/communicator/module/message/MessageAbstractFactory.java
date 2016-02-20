package com.cloud.communicator.module.message;

import com.cloud.communicator.AbstractFactory;
import com.cloud.communicator.module.message.dto.MessageDTO;
import com.cloud.communicator.module.user.User;

public interface MessageAbstractFactory extends AbstractFactory {
    Message createFromDTO(MessageDTO messageDTO, User author);
    Message createFromObject(Object[] messageObject);
}
