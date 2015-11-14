package org.pac4j.demo.spring;

import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.credentials.CasCredentials;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.RequiresHttpAction;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.profile.UserProfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class Application {

    @Inject
    Config config;

    private static final Logger logger = Logger.getLogger(Application.class);

    @RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws RequiresHttpAction {
        return index(request, response, map);
    }

    @RequestMapping("/index.html")
    public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws RequiresHttpAction {
        final WebContext context = new J2EContext(request, response);
        map.put("profile", getProfile(context));
        final Clients clients = config.getClients();


        final CasClient casClient = (CasClient) clients.findClient(context, "CasClient");
        //logs debug message
        if(logger.isDebugEnabled()){
            logger.debug(casClient.getCasLoginUrl());
            logger.debug(casClient.getClientType());
            logger.debug(casClient.toString());
        }

        map.put("urlCas", (String) casClient.getRedirectAction(context, false).getLocation());

        return "index";
    }

    private UserProfile getProfile(WebContext context) {
        final ProfileManager manager = new ProfileManager(context);
        return manager.get(true);
    }

    private String getStringProfile(WebContext context) {
        final UserProfile profile = getProfile(context);
        if (profile == null) {
            return "";
        } else {
            return profile.toString();
        }
    }

    @RequestMapping("/cas/index.html")
    public String cas(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        return protectedIndex(request, response, map);
    }


    @RequestMapping("/protected/index.html")
    public String protect(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        return protectedIndex(request, response, map);
    }

    @RequestMapping("/admin/index.html")
    public String protectAdmin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        return "admin";
    }


    protected String protectedIndex(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        final WebContext context = new J2EContext(request, response);
        map.put("profile", getStringProfile(context));
        return "protectedIndex";
    }
}