package com.cloud.communicator.module.message;


import com.cloud.communicator.module.message.service.MessageReceiverService;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.util.UserUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

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

    @RequestMapping(value = MessageUrls.Api.MESSAGE, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<String> sendMessage(@ModelAttribute @Valid MessageDTO messageDTO,
                                              ModelMap model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

        String[] args = {};

        String receiversField = messageDTO.getReceivers();
        String[] receivers;
        receivers = receiversField.split(" ");

        //sprawdzenie poprawności wprowadzonych danych oraz utworzenie wiadomości

            Message message = new Message();

            //ustawienieautora, tematu, tekstu
            message.setAuthor(userService.findUserById(UserUtils.getUserId(request, response)));
            message.setTopic(messageDTO.getTopic());
            message.setText(messageDTO.getText());
            messageService.saveMessage(message);

            for (String r : receivers) {
                MessageReceiver messageReceiver = new MessageReceiver();
                messageReceiver.setReceiverId(userService.getUserIdByUsername(r));
                messageReceiver.setMessageId(message.getId());
                messageReceiver.setIsRead(false);

            }

        return new ResponseEntity<String>( messageSource.getMessage("message.success.send", args, locale), HttpStatus.OK);
    }




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
