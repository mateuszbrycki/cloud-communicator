package com.cloud.communicator.module.contact;

import com.cloud.communicator.module.contact.service.UserContactService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(UserContactUrls.Api.CONTACT)
public class RestUserContactController {

    @Inject
    UserContactService userContactService;

    @Inject
    UserService userService;

    private static final Logger logger = Logger.getLogger(RestUserContactController.class);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<List<UserContact>> addContact(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid UserContactDTO userContactDTO) {

        Integer userId = UserUtils.getUserId(request, response);

        List<User> userContactList = this.prepareContactsInput(userContactDTO.getContacts());
        User user = this.userService.findUserById(userId);

        //TODO mbrycki command object
        this.userContactService.addUsersToAddressBook(user, userContactList);

        return new ResponseEntity<>(userContactService.findContactsByUserId(userId), HttpStatus.OK);
    }

    private List<User> prepareContactsInput(String contactsInput) {
        List<User> userContactList = new ArrayList<>();

        for(String contactUser : contactsInput.split(" ")) {

            User contactObject;
            try {
                contactObject = this.userService.findUserById(Integer.parseInt(contactUser));
            } catch(NumberFormatException e) {
                contactObject = this.userService.findUserByUsername(contactUser);
            }

            if(contactObject != null) {
                userContactList.add(contactObject);
            }
        }

        return userContactList;
    }

    @RequestMapping(value = UserContactUrls.Api.USER_CONTACT_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<List<UserContact>> deleteContact(@PathVariable("personInBook") Integer personInBookId,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);

        this.userContactService.deleteUserContact(userId, personInBookId);
        return new ResponseEntity<>(this.userContactService.findContactsByUserId(userId), HttpStatus.OK);
    }

}
