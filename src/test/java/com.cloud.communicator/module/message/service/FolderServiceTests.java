package com.cloud.communicator.module.message.service;

import com.cloud.communicator.config.AppConfig;
import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.user.User;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class FolderServiceTests {

    private static final Logger logger = Logger.getLogger(FolderServiceTests.class);

    @Inject
    FolderService folderService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    UserService userService;

    private User testUser;
    private Folder testFolder;

    @Before
    public void initObjects() {
        testUser = new User();
        testUser.setMail("test@test.gmail.com");
        testUser.setUsername("testusername");
        testUser.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword");
        userService.registerUser(testUser);

        testFolder = new Folder();
        testFolder.setName("Test folder");
        testFolder.setDescription("Test folder");
        testFolder.setOwner(testUser);
        folderService.saveFolder(testFolder);
    }

    @Test
    public void saveFolder() {

        Folder savedFolder = folderService.findFolderById(testFolder.getId());

        assertEquals(savedFolder.getId(), testFolder.getId());
        assertEquals(savedFolder.getName(), testFolder.getName());
    }

    @Test
    public void updateFolder() {

        testFolder.setName("Updated folder name");
        folderService.updateFolder(testFolder);

        Folder updatedFolder = folderService.findFolderById(testFolder.getId());

        assertEquals(updatedFolder.getId(), testFolder.getId());
        assertEquals(updatedFolder.getName(), testFolder.getName());
    }

    @Test
    public void deleteFolder() {

        folderService.deleteFolder(testFolder);

        Folder deletedFolder = folderService.findFolderById(testFolder.getId());

        assertNull(deletedFolder);
    }

    @Test
    public void findUserFoldersByUserId() {
        List<Folder> userFolders = folderService.findUserFoldersByUserId(testUser.getId());

        assertEquals(2, userFolders.size());
    }

    @Test
    public void deleteUserFoldersByUserId() {
        folderService.deleteUserFoldersByUserId(testUser.getId());

        List<Folder> userFolders = folderService.findUserFoldersByUserId(testUser.getId());
        assertEquals(0, userFolders.size());
    }

    @After
    public void clearDatabase() {
        folderService.deleteFolder(testFolder.getId());
        userService.deleteUserById(testUser.getId());
    }
}
