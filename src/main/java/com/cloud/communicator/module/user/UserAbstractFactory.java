package com.cloud.communicator.module.user;

import com.cloud.communicator.AbstractFactory;
import com.cloud.communicator.module.user.dto.UserDTO;

public interface UserAbstractFactory extends AbstractFactory {
    User createFromDTO(UserDTO userDTO);
    User createFromObject(Object[] userObject);
}
