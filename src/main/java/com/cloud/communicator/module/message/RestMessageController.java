package com.cloud.communicator.module.message;

import com.cloud.communicator.module.message.dto.MessageDTO;
import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.module.message.service.UserMessageFolderService;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(MessageUrls.Api.MESSAGE)
public class RestMessageController {

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    @Inject
    private MessageSource messageSource;

    @Inject
    private MessageReceiverService messageReceiverService;

    @Inject
    private UserMessageFolderService userMessageFolderService;

    @Inject
    private MessageAbstractFactory messageFactory;

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    private String[] args = {};

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<String> sendMessage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid MessageDTO messageDTO,
                                              ModelMap model,
                                              Locale locale) {

        logger.debug(messageDTO);

        User author = this.userService.findUserById(UserUtils.getUserId(request, response));
        Message message = this.messageFactory.createFromDTO(messageDTO, author);

        String receiversField = messageDTO.getReceivers();
        List<User> receiversList = this.prepareReceiverList(receiversField);

        //TODO mbrycki zmiana na command
        this.messageService.sendMessage(message, receiversList);

        return new ResponseEntity<String>(this.messageSource.getMessage("message.success.send", args, locale), HttpStatus.OK);
    }

    private List<User> prepareReceiverList(String receiversField) {

        List<User> receiversList = new ArrayList<User>();

        for(String receiver : receiversField.split(" ")) {

            User receiverObject;
            try {
                receiverObject = userService.findUserById(Integer.parseInt(receiver));
            } catch(NumberFormatException e) {
                receiverObject = userService.findUserByUsername(receiver);
            }

            receiversList.add(receiverObject);
        }

        return receiversList;
    }

    @RequestMapping(value = MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_ID, method = RequestMethod.GET)
    public ResponseEntity<Message> markAsUnread(@PathVariable("messageId") Integer id,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {

        try {
            Integer userId = UserUtils.getUserId(request, response);
            //TODO mbrycki command object
            messageReceiverService.changeMessageReadStatus(id, userId);
        } catch (NullPointerException e) {
            return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.OK);
    }

    @RequestMapping(value = MessageUrls.Api.MESSAGE_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteMessage(@PathVariable("messageId") Integer id,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {

        try {
            Integer userId = UserUtils.getUserId(request, response);
            messageReceiverService.deleteMessageForUser(id, userId);
        } catch (NullPointerException e) {
            return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.OK);
    }

    @RequestMapping(value = MessageUrls.Api.MESSAGE_ID, method = RequestMethod.GET)
    public ResponseEntity<Object> getMessage(@PathVariable("messageId") Integer id,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 Locale locale) {

        Message message = this.messageService.findMessageById(id);
        Integer userId = UserUtils.getUserId(request, response);

        Boolean isUserAllowedToSeeMessage = this.isUserAllowedToSeeMessage(message, userId);
        if (isUserAllowedToSeeMessage) {
            this.messageReceiverService.setMessageAsRead(message.getId(), userId);
            return new ResponseEntity<Object>(message, HttpStatus.OK);
        }

        this.messageReceiverService.setMessageAsRead(message.getId(), userId);
        return new ResponseEntity<Object>(this.messageSource.getMessage("message.user.notallowed", args, locale), HttpStatus.FORBIDDEN);
    }

    private Boolean isUserAllowedToSeeMessage(Message message, Integer userId) {
        return messageService.isAllowedToSeeMessage(message, userId);
    }

    @RequestMapping(value = MessageUrls.Api.MESSAGE_FOLDER_ID, method = RequestMethod.GET)
    public ResponseEntity<Object> updateMessageFolder(@PathVariable("messageId") Integer messageId,
                                                      @PathVariable("folderId") Integer folderId,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response){
        Integer userId =  UserUtils.getUserId(request,response);
        UserMessageFolder userMessageFolder =
                this.userMessageFolderService.getUserMessageFolder(messageId, userId);

        if(userMessageFolder == null) {
            return new ResponseEntity<Object>(userMessageFolder, HttpStatus.FORBIDDEN);
        }

        userMessageFolder.setFolderId(folderId);

        this.userMessageFolderService.updateUserMessageFolder(messageId, userId, folderId);

        return new ResponseEntity<Object>(userMessageFolder, HttpStatus.OK);
    }
}
