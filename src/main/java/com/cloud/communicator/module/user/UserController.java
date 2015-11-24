package com.cloud.communicator.module.user;



import com.cloud.communicator.module.userrole.service.UserRoleService;
import com.cloud.communicator.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping(UserUrls.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @Inject
    private UserRoleService userRoleService;

    private String viewPath = "controller/user/";

    @RequestMapping(value = UserUrls.USER_LOGOUT, method = RequestMethod.GET)
    public String logoutPage() {
        //TODO mbrycki redirect to cas logout URL
        return "redirect:/";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.GET)
    public String registerFormPage() {

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.POST)
    public String registerPage(@RequestParam("mail") String mail,
                               @RequestParam("password") String password,
                               @RequestParam("password_repeat") String passwordRepeat,
                               ModelMap model) {

        Boolean equals = password.equals(passwordRepeat);
        Boolean userExists = userService.checkIfUserWithMailExists(mail);

        if(userExists == false && equals) {
            User user = new User();
            user.setMail(mail);
            user.setPassword(password);
            user.setIsEnabled(User.DEFAULT_IS_ENABLED);
            user.setIsPublic(User.DEFAULT_IS_PUBLIC);
            user.setRole(userRoleService.findByName(User.DEFAULT_ROLE));

            userService.saveUser(user);

            model.addAttribute("success", "User successfully created. Please log in.");

        } else {

            if(userExists != null) {
                model.addAttribute("error", "Passed mail is in use.");
            } else {
                model.addAttribute("error", "Passwords are not equals.");
            }

        }

        return this.viewPath + "register";
    }
}
