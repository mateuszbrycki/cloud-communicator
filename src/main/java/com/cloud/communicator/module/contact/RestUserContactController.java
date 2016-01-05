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

    @Inject
    private MessageSource messageSource;

    private static final Logger logger = Logger.getLogger(RestUserContactController.class);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<List<UserContact>> addContact(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody @Valid UserContactDTO userContactDTO) {

        Integer userId = UserUtils.getUserId(request, response);

        List<User> userContactList = new ArrayList<>();

        for(String contactUser : userContactDTO.getContacts().split(" ")) {

            User contactObject;
            try {
                contactObject = userService.findUserById(Integer.parseInt(contactUser));
            } catch(NumberFormatException e) {
                contactObject = userService.findUserByUsername(contactUser);
            }

            if(contactObject != null) {
                userContactList.add(contactObject);
            }
        }
        userContactService.addUsersToAddressBook(userService.findUserById(userId), userContactList);

        return new ResponseEntity<>(userContactService.findContactsByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = UserContactUrls.Api.USER_CONTACT_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<List<UserContact>> deleteContact(@PathVariable("personInBook") Integer personInBookId,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {

        Integer userId = UserUtils.getUserId(request, response);

        userContactService.deleteUserContact(userId, personInBookId);

        return new ResponseEntity<>(userContactService.findContactsByUserId(userId), HttpStatus.OK);
    }

}
