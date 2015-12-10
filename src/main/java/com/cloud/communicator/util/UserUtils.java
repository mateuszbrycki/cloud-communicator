package com.cloud.communicator.util;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.profile.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mateusz on 25.11.2015.
 */
public class UserUtils {

    public static Integer getUserId(HttpServletRequest request, HttpServletResponse response) {
        UserProfile userProfile = UserUtils.getUserProfile(request, response);

        return Integer.parseInt(userProfile.getId());
    }

    public static Boolean isAutheniticated(HttpServletRequest request, HttpServletResponse response) {
        final WebContext context = new J2EContext(request, response);
        ProfileManager profileManager = new ProfileManager(context);

        return profileManager.isAuthenticated();
    }

    public static UserProfile getUserProfile(HttpServletRequest request, HttpServletResponse response) {
        final WebContext context = new J2EContext(request, response);
        ProfileManager profileManager = new ProfileManager(context);

        return profileManager.get(true);
    }
}
