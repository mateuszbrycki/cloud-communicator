package com.cloud.communicator.module.base;

import com.cloud.communicator.module.user.UserUrls;
import com.cloud.communicator.util.UserUtils;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.RequiresHttpAction;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class BaseController {

    private String viewPath = "controller/default/";

    @RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws RequiresHttpAction {
        final WebContext context = new J2EContext(request, response);

        if(UserUtils.isAutheniticated(context)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "main";
    }
}
