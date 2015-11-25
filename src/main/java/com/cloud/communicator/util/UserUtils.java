package com.cloud.communicator.util;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.ProfileManager;

/**
 * Created by Mateusz on 25.11.2015.
 */
public class UserUtils {

    public static Boolean isAutheniticated(WebContext webContext) {

        ProfileManager profileManager = new ProfileManager(webContext);
        return profileManager.isAuthenticated();
    }
}
