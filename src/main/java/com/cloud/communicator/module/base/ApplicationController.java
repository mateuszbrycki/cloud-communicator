package com.cloud.communicator.module.base;

import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.message.Message;
import com.cloud.communicator.module.message.service.FolderService;
import com.cloud.communicator.module.message.service.MessageService;
import com.cloud.communicator.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Mateusz on 25.11.2015.
 */
@Controller
@RequestMapping(BaseUrls.APPLICATION)
public class ApplicationController {

    @Inject
    private MessageService messageService;

    @Inject
    private FolderService folderService;

    private static final Logger logger = Logger.getLogger(ApplicationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String listAction(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Integer userId = UserUtils.getUserId(request, response);
        logger.debug(userId);

        List<Folder> folders = folderService.findUserFoldersByUserId(userId);
        model.addAttribute("folders", folders);
        logger.debug(folders);

        List<Message> messages = messageService.findUserInboxMessages(userId);
        model.addAttribute("messages", messages);
        logger.debug(messages);

        return "controller/message/inbox";
    }

}
