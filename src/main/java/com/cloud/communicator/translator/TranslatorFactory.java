package com.cloud.communicator.translator;

import com.cloud.communicator.module.folder.Folder;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.user.User;


public class TranslatorFactory {

    public Translator<Message> getMessageTranslator() {
        return new Translator<Message>();
    }

    public Translator<Folder> getFolderTranslator() {
        return new Translator<Folder>();
    }

    public Translator<User> getUserTranslator() {
        return new Translator<User>();
    }

}
