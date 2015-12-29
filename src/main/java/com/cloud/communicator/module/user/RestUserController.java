package com.cloud.communicator.module.user;

import com.cloud.communicator.Select2Response;
import com.cloud.communicator.module.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserUrls.Api.USER)
public class RestUserController {

    @Inject
    UserService userService;

    @RequestMapping(value = {UserUrls.Api.USERNAME, "/"}, method = RequestMethod.GET)
    public ResponseEntity<Object> getMessage(@RequestParam Map<String,String> allRequestParams) {

        String usernameParameter = allRequestParams.get("username");

        List<Select2Response> response = new ArrayList<>();

        for(User user : userService.findUsersByUsername(usernameParameter)) {
            response.add(new Select2Response(String.valueOf(user.getId()), user.getUsername()));
        }

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
