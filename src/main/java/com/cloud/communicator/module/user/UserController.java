package com.cloud.communicator.module.user;

import com.cloud.communicator.module.base.BaseUrls;
import com.cloud.communicator.module.message.Folder;
import com.cloud.communicator.module.message.service.FolderService;
import com.cloud.communicator.module.user.service.UserService;
import com.cloud.communicator.module.userrole.service.UserRoleService;
import com.cloud.communicator.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(UserUrls.USER)
public class UserController {

    @Inject
    private UserService userService;


    @Inject
    private FolderService folderService;

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

        if (UserUtils.isAutheniticated(request, response)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    public String registerPage(@ModelAttribute @Valid UserDTO userDTO,
                               ModelMap model, Locale locale) {

        Boolean passwordsAreEqual = userDTO.getPassword().equals(userDTO.getPasswordRepeat());

        String[] args = {};

        logger.debug(userDTO);

        if (passwordsAreEqual) {

            //map UserDTO to User
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setMail(userDTO.getMail());
            user.setPassword(userDTO.getPassword());
            user.setIsActive(User.DEFAULT_IS_ACTIVE);
            user.setRole(userRoleService.findByName(User.DEFAULT_ROLE));

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
}
