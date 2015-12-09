package com.cloud.communicator.module.message;

import com.cloud.communicator.module.base.ApplicationController;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.module.user.UserController;
import com.cloud.communicator.module.user.UserDTO;
import com.cloud.communicator.module.user.UserUrls;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.user.service.UserServiceImpl;
import com.cloud.communicator.util.UserUtils;
import org.hibernate.service.spi.InjectService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private ApplicationController applicationController;
    

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
                messageRe
            }

        return new ResponseEntity<String>( messageSource.getMessage("message.success.send", args, locale), HttpStatus.OK);
    }
}
