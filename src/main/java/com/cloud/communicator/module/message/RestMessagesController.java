package com.cloud.communicator.module.message;

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
import java.util.List;

@RestController
@RequestMapping(MessageUrls.Api.MESSAGES)
public class RestMessagesController {

    @Inject
    private MessageService messageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> inboxList(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);

        List<Message> messages = messageService.findUserInboxMessages(userId);
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = MessageUrls.Api.MESSAGES_FOLDER_ID, method = RequestMethod.GET)
    public ResponseEntity<List<Message>> folderMessagesList(
            @PathVariable("folderId") Integer folderId,
            HttpServletRequest request,
            HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);

        List<Message> messages = messageService.findUserFolderMessages(userId, folderId);
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }


}
