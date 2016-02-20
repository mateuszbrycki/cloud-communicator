package com.cloud.communicator.module.search;


import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(SearchUrls.Api.SEARCH)
public class RestSearchController {

    @Inject
    private MessageService messageService;

    private static final Logger logger = Logger.getLogger(RestSearchController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<Message>> searchList(
            @RequestBody SearchDTO searchPhrase,
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.debug(searchPhrase);

        Integer userId = UserUtils.getUserId(request, response);

        List<Message> messages = this.messageService.findUserMessagesByPhrase(userId, searchPhrase.getPhrase());
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

}
