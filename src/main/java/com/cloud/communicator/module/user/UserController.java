package com.cloud.communicator.module.user;

import com.cloud.communicator.module.base.BaseUrls;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.util.UserUtils;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import org.apache.log4j.Logger;

@Controller
@RequestMapping(UserUrls.USER)
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private UserRoleService userRoleService;

    @Inject
    private MessageSource messageSource;

    private static final Logger logger = Logger.getLogger(UserController.class);

    private String viewPath = "controller/user/";

    @RequestMapping(value = UserUrls.USER_LOGOUT, method = RequestMethod.GET)
    public String logoutPage() {
        //TODO mbrycki redirect to cas logout URL
        return "redirect:/";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.GET)
    public String registerFormPage(HttpServletRequest request, HttpServletResponse response) {

        final WebContext context = new J2EContext(request, response);

        if(UserUtils.isAutheniticated(context)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.POST)
    public String registerPage(@RequestParam("username") String username,
                               @RequestParam("mail") String mail,
                               @RequestParam("password") String password,
                               @RequestParam("password_repeat") String passwordRepeat,
                               ModelMap model, Locale locale) {

        Boolean equals = password.equals(passwordRepeat);
        Boolean userMailExists = userService.checkIfUserWithMailExists(mail);
        Boolean usernameExist = userService.checkIfUserWithUsernameExists(username);

        String[] args = {};

        if(!userMailExists && !usernameExist && equals) {
            User user = new User();
            user.setUsername(username);
            user.setMail(mail);
            user.setPassword(password);
            user.setIsEnabled(User.DEFAULT_IS_ENABLED);
            user.setIsPublic(User.DEFAULT_IS_PUBLIC);
            user.setRole(userRoleService.findByName(User.DEFAULT_ROLE));

            userService.saveUser(user);

            model.addAttribute("success", messageSource.getMessage("user.message.success.register", args, locale));

        } else {

            if (userMailExists) {
                model.addAttribute("error", messageSource.getMessage("user.message.error.mail", args, locale));
            } else if (usernameExist) {
                model.addAttribute("error",  messageSource.getMessage("user.message.error.username", args, locale));
            }  else {
                model.addAttribute("error",  messageSource.getMessage("user.message.error.passwords", args, locale));
            }

        }

        return this.viewPath + "register";
    }
}
