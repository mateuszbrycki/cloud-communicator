package com.cloud.communicator.module.message;


import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.message.service.MessageService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    MessageReceiverService messageReceiverService;

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<String> sendMessage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid MessageDTO messageDTO,
                                              ModelMap model,
                                              Locale locale) {

        logger.debug(messageDTO);

        String[] args = {};

        String receiversField = messageDTO.getReceivers();

        //map DTO to message object
        Message message = new Message();
        message.setAuthor(userService.findUserById(UserUtils.getUserId(request, response)));
        message.setTopic(messageDTO.getTopic());
        message.setText(messageDTO.getText());
        message.setSendDate(new Date());

        List<User> receiversList = new ArrayList<User>();
        for(String receiver : receiversField.split(" ")) {
            User receiverObject = userService.findUserByUsername(receiver);

            if(receiverObject == null) {
                return new ResponseEntity<String>(messageSource.getMessage("message.receiver.not.found", args, locale), HttpStatus.NOT_ACCEPTABLE);
            }

            receiversList.add(receiverObject);
        }

        messageService.sendMessage(message, receiversList);

        return new ResponseEntity<String>(messageSource.getMessage("message.success.send", args, locale), HttpStatus.OK);
    }


    @RequestMapping(value = MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_ID, method = RequestMethod.GET)
    public ResponseEntity<Message> markAsUnread(@PathVariable("messageId") Integer id,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {

        try {
            Integer userId = UserUtils.getUserId(request, response);
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

        String[] args = {};
        Message message = messageService.findMessageById(id);
        Integer userId = UserUtils.getUserId(request, response);

        if (messageService.isAllowedToSeeMessage(message, userId))
        {
            messageReceiverService.setMessageAsRead(message.getId(), userId);
            return new ResponseEntity<Object>(message, HttpStatus.OK);
        }

        return new ResponseEntity<Object>(messageSource.getMessage("message.user.notallowed", args, locale), HttpStatus.FORBIDDEN);
    }
}
