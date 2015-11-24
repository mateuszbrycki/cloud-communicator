package com.cloud.communicator.module.user;


import com.cloud.communicator.api.UrlSpace;

/**
 * Created by Mateusz on 27.09.2015.
 */
public class UserUrls implements UrlSpace {
    public static final String USER = "/user";

    public static final String USER_LOGOUT = "/logout";
    public static final String USER_REGISTER = "/register";

    public static final String USER_REGISTER_FORM = USER + USER_REGISTER;
}