package com.cloud.communicator.module.folder.factory;

import com.cloud.communicator.module.folder.Folder;
import com.cloud.communicator.module.folder.FolderAbstractFactory;
import com.cloud.communicator.module.folder.dto.FolderDTO;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;

import javax.inject.Inject;
//TODO mbrycki broken SRP
public class FolderFactory implements FolderAbstractFactory{

    @Inject
    private UserService userService;

    @Override
    public Folder createFromDTO(FolderDTO folderDTO, User owner) {
        Folder folder = new Folder();

        if(folderDTO.getId() != null) {
            folder.setId(folderDTO.getId());
        }

        folder.setName(folderDTO.getName());
        folder.setDescription(folderDTO.getDescription());
        folder.setLabelColor(folderDTO.getLabel());
        folder.setIsUserDefaultFolder(false);
        folder.setOwner(owner);

        return folder;
    }

    @Override
    public Folder createFromObject(Object[] folderObject) {

        Folder folder = new Folder();
        folder.setId((Integer) folderObject[0]);
        folder.setName((String) folderObject[1]);
        folder.setDescription((String) folderObject[2]);
        folder.setLabelColor((String) folderObject[3]);
        folder.setOwner(this.userService.findUserById((Integer) folderObject[4]));
        folder.setIsUserDefaultFolder((Boolean) folderObject[5]);

        if(folderObject.length > 8) {
            folder.setUnreadMessages((Integer) folderObject[8]);
        }

        return folder;
    }
}
