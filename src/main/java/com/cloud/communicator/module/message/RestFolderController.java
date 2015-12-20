package com.cloud.communicator.module.message;


import com.cloud.communicator.module.message.service.FolderService;
import com.cloud.communicator.module.message.service.MessageService;
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
import java.util.Locale;

@RestController
@RequestMapping(FolderUrls.Api.FOLDER)
public class RestFolderController {

    @Inject
    private MessageSource messageSource;

    @Inject
    private FolderService folderService;

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Folder> sendMessage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid FolderDTO folderDTO,
                                              ModelMap model,
                                              Locale locale) {

        logger.debug(folderDTO);

        String[] args = {};

        Folder folder = new Folder();
        folder.setName(folderDTO.getName());
        folder.setDescription(folderDTO.getDescription());
        folder.setLabelColor(folderDTO.getLabel());
        folder.setOwner(this.userService.findUserById(UserUtils.getUserId(request, response)));
        folderService.saveFolder(folder);

        return new ResponseEntity<Folder>(folder, HttpStatus.OK);
    }
}
