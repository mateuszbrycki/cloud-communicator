package com.cloud.communicator.module.folder;

import com.cloud.communicator.AbstractFactory;
import com.cloud.communicator.module.folder.dto.FolderDTO;
import com.cloud.communicator.module.user.User;

public interface FolderAbstractFactory extends AbstractFactory {
    Folder createFromDTO(FolderDTO folderDTO, User owner);
    Folder createFromObject(Object[] folderObject);
}
