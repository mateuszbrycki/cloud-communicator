package com.cloud.communicator.controller;

import org.pac4j.core.exception.RequiresHttpAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Application {

    @RequestMapping("/")
    public String root() throws RequiresHttpAction {
        return "controller/user/register";
    }

    @RequestMapping("/cas/index.html")
    public String casIndex() throws RequiresHttpAction {
        return "admin";
    }
}
