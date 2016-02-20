package com.cloud.communicator.config.factory;

import com.cloud.communicator.module.folder.FolderAbstractFactory;
import com.cloud.communicator.module.folder.factory.FolderFactory;
import com.cloud.communicator.module.message.MessageAbstractFactory;
import com.cloud.communicator.module.message.factory.MessageFactory;
import com.cloud.communicator.module.user.UserAbstractFactory;
import com.cloud.communicator.module.user.factory.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {

    @Bean
    public UserAbstractFactory userAbstractFactory() {
        return new UserFactory();
    }

    @Bean
    public MessageAbstractFactory messageAbstractFactory() {
        return new MessageFactory();
    }

    @Bean
    public FolderAbstractFactory folderAbstractFactory() {
        return new FolderFactory();
    }
}
