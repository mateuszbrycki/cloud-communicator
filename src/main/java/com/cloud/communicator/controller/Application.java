package com.cloud.communicator.controller;

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
public class Application {

    @RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws RequiresHttpAction {
        final WebContext context = new J2EContext(request, response);

        ProfileManager profileManager = new ProfileManager(context);
        if(profileManager.isAuthenticated()) {
            return "redirect:/cas/index.html";
        }

        return "controller/user/register";
    }

    @RequestMapping("/cas/index.html")
    public String casIndex() throws RequiresHttpAction {
        return "admin";
    }
}
