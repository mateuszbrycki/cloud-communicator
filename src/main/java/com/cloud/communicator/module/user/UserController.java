package com.cloud.communicator.module.user;

import com.cloud.communicator.module.base.BaseUrls;
import com.cloud.communicator.module.user.dto.UserDTO;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(UserUrls.USER)
public class UserController {
    @Inject
    private UserAbstractFactory userFactory;

    @Inject
    private UserService userService;

    @Inject
    private MessageSource messageSource;

    @Inject
    private Environment environment;

    private static final Logger logger = Logger.getLogger(UserController.class);

    private String viewPath = "controller/user/";

    @RequestMapping(value = UserUrls.USER_LOGOUT, method = RequestMethod.GET)
    public String logoutPage() {

        return "redirect:" + environment.getProperty("pac4j.application.logout");
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.GET)
    public String registerFormPage(HttpServletRequest request, HttpServletResponse response) {

        if (UserUtils.isAutheniticated(request, response)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    public String registerPage(@ModelAttribute @Valid UserDTO userDTO,
                               ModelMap model, Locale locale) {

        String[] args = {};
        logger.debug(userDTO);

        Boolean passwordsAreEqual = userDTO.getPassword().equals(userDTO.getPasswordRepeat());
        if (passwordsAreEqual) {

            User user = userFactory.createFromDTO(userDTO);

            //TODO mbrycki command object
            if(this.userService.registerUser(user)) {

                model.addAttribute("success", messageSource.getMessage("user.message.success.register", args, locale));
            } else {
                model.addAttribute("error", messageSource.getMessage("user.message.error", args, locale));
            }
        } else {
            model.addAttribute("error", messageSource.getMessage("user.message.error.passwords", args, locale));

        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_MANAGEMENT, method = RequestMethod.GET)
    public String managementPage(HttpServletRequest request, HttpServletResponse response) {

        return this.viewPath + "management";
    }

    @RequestMapping(value = UserUrls.USER_PASSWORD_CHANGE, method = RequestMethod.POST)
    public String changePasswordPage(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("password_old") String oldPassword,
                                     @RequestParam("password") String password,
                                     @RequestParam("password_repeat") String passwordRepeat,
                                     RedirectAttributes attributes, Locale locale) {

        Integer userId = UserUtils.getUserId(request, response);
        String[] args = {};

        User user = userService.findUserById(userId);

        //check if passwords are equals
        Boolean passwordsAreEqual = password.equals(passwordRepeat);
        Boolean oldPasswordIsGood = user.getPassword().equals(oldPassword);

        if (passwordsAreEqual && oldPasswordIsGood) {
            //update user
            user.setPassword(password);
            userService.updateUser(user);

            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.passwords", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.passwords", args, locale));
        }

        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }

    @RequestMapping(value = UserUrls.USER_USERNAME_CHANGE, method = RequestMethod.POST)
    public String changeUsernamePage(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("username") String username,
                                     RedirectAttributes attributes, Locale locale) {

        Integer userId = UserUtils.getUserId(request, response);

        String[] args = {};

        //check if username doesn't exist in database
        Boolean usernameExists = userService.checkIfUserWithUsernameExists(username);

        if(!usernameExists) {
            //update user
            User user = userService.findUserById(userId);
            user.setUsername(username);
            userService.updateUser(user);

            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.username", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.username", args, locale));
        }

        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }

    @RequestMapping(value = UserUrls.USER_DELETE, method = RequestMethod.POST)
    public String deleteAccountAction(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam("password") String password,
                                      RedirectAttributes attributes, Locale locale) {
        Integer userId = UserUtils.getUserId(request, response);

        User user = userService.findUserById(userId);

        String[] args = {};

        //delete user
        if(user.getPassword().equals(password)) {
            userService.deleteUserById(userId);

            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.delete", args, locale));
            return "redirect:" + UserUrls.USER_LOGOUT_FULL;
        }

        attributes.addFlashAttribute("error",messageSource.getMessage("user.message.error.delete", args, locale));
        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }
}
