package com.cloud.communicator.module.message;

import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.util.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(MessageUrls.Api.MESSAGE)
public class RestMessageController {

    @Inject
    MessageService messageService;

    @Inject
    MessageReceiverService messageReceiverService;

    @RequestMapping(value = MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_ID, method = RequestMethod.GET)
    public ResponseEntity<Message> markAsUnread(@PathVariable("messageId") Integer id,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {

        try {
            Integer userId = UserUtils.getUserId(request, response);
            messageReceiverService.changeMessageReadStatus(id, userId);
        } catch(NullPointerException e) {
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
        } catch(NullPointerException e) {
            return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Message>(messageService.findMessageById(id), HttpStatus.OK);
    }

}
