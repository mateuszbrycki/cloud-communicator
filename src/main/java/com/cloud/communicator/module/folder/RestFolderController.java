package com.cloud.communicator.module.folder;


import com.cloud.communicator.module.folder.dto.FolderDTO;
import com.cloud.communicator.module.folder.service.FolderService;
import com.cloud.communicator.module.message.RestMessageController;
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
import java.util.List;
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

    @Inject
    private FolderAbstractFactory folderFactory;

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Folder> addFolder(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid FolderDTO folderDTO,
                                              ModelMap model,
                                              Locale locale) {

        logger.debug(folderDTO);
        String[] args = {};

        User owner = this.userService.findUserById(UserUtils.getUserId(request, response));
        Folder folder = folderFactory.createFromDTO(folderDTO, owner);

        folderService.saveFolder(folder);

        return new ResponseEntity<Folder>(folder, HttpStatus.OK);
    }

    @RequestMapping(value = FolderUrls.Api.FOLDER_ID, method = RequestMethod.GET)
    public ResponseEntity<Folder> getFolder(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("folderId") Integer folderId) {
        Integer userId = UserUtils.getUserId(request, response);

        Folder folder = folderService.findFolderById(folderId, userId);

        if(folder == null) {
            return new ResponseEntity<>(new Folder(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Folder>(folder, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Folder> addFolder(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestBody @Valid FolderDTO folderDTO) {

        Integer userId = UserUtils.getUserId(request, response);

        Folder folder = folderService.findFolderById(folderDTO.getId(), userId);

        if(folder == null) {
            return new ResponseEntity<Folder>(folder, HttpStatus.FORBIDDEN);
        }

        User owner = this.userService.findUserById(UserUtils.getUserId(request, response));

        folder = folderFactory.createFromDTO(folderDTO, owner);
        folderService.updateFolder(folder);

        return new ResponseEntity<Folder>(folder, HttpStatus.OK);
    }

    @RequestMapping(value = FolderUrls.Api.FOLDER_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<List<Folder>> deleteFolder(@PathVariable("folderId") Integer folderId,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);
        try {
           folderService.deleteFolder(folderId, userId);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(folderService.findUserFoldersByUserId(userId), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(folderService.findUserFoldersByUserId(userId), HttpStatus.OK);
    }
}
