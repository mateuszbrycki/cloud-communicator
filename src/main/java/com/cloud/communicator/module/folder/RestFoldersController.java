package com.cloud.communicator.module.folder;

import com.cloud.communicator.module.folder.service.FolderService;
import com.cloud.communicator.util.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping(FolderUrls.Api.FOLDERS)
public class RestFoldersController {

    @Inject
    private FolderService folderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Folder>> inboxList(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);
        List<Folder> folders = folderService.findUserFoldersByUserId(userId);

        return new ResponseEntity<List<Folder>>(folders, HttpStatus.OK);
    }

}

