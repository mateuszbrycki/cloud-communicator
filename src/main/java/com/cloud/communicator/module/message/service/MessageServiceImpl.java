package com.cloud.communicator.module.message.service;

import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.MessageReceiver;
import com.cloud.communicator.module.message.UserMessageFolder;
import com.cloud.communicator.module.message.dao.MessageDao;
import com.cloud.communicator.module.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;


@Service("messageService")
@Transactional(value = "transactionManagerPostgreSQL")
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageDao messageDao;

    @Inject
    FolderService folderService;

    @Inject
    MessageReceiverService messageReceiverService;

    @Inject
    UserMessageFolderService userMessageFolderService;


    @Override
    public void saveMessage(Message message) { messageDao.saveMessage(message); }

    @Override
    public void updateMessage(Message message) { messageDao.updateMessage(message); }

    @Override
    public void deleteMessage(Message message) { messageDao.deleteMessage(message); }

    @Override
    public void deleteMessage(Integer id) { messageDao.deleteMessage(id); }

    @Override
    public List<Message> findUserInboxMessages(Integer userId) {
        return messageDao.findUserInboxMessages(userId);
    }

    @Override
    public List<Message> findUserFolderMessages(Integer userId, Integer folderId) {
        return messageDao.findUserFolderMessages(userId, folderId);
    }

    @Override
    public List<Message> findUserMessagesByPhrase(Integer userId, String phrase) {
        return messageDao.findUserMessagesByPhrase(userId, phrase);
    }

    @Override
    public Message findMessageById(Integer messageId) {
        return this.messageDao.findMessageById(messageId);
    }

    @Override
    public Boolean isAllowedToSeeMessage(Message message, Integer userID){

        if(message.getAuthor().getId() == userID) {
            return true;
        }

        for(MessageReceiver messageReceiver: message.getReceivers())
        {
            if(messageReceiver.getReceiverId().equals(userID))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void sendMessage(Message message, List<User> receivers) {

        //save message
        this.saveMessage(message);

        for(User receiver : receivers) {

            MessageReceiver messageReceiver = new MessageReceiver();

            messageReceiver.setReceiverId(receiver.getId());
            messageReceiver.setMessageId(message.getId());
            messageReceiver.setIsRead(false);

            //save receiver
            this.messageReceiverService.saveMessageReceiver(messageReceiver);

            UserMessageFolder userMessageFolder = new UserMessageFolder();
            userMessageFolder.setMessageId(message.getId());
            userMessageFolder.setUserId(receiver.getId());

            Folder defaultUserFolder = this.folderService.findUserDefaultFolder(receiver.getId());
            userMessageFolder.setFolderId(defaultUserFolder.getId());

            //assign message to user default folder - inbox
            this.userMessageFolderService.saveUserMessageFolder(userMessageFolder);
        }

    }
}
