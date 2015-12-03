package com.cloud.communicator.module.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mateusz on 25.11.2015.
 */
@Controller
@RequestMapping(BaseUrls.APPLICATION)
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public String listAction() {

        return "controller/message/list";
    }


}
