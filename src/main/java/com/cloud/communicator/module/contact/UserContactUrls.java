package com.cloud.communicator.module.contact;

import com.cloud.communicator.api.UrlSpace;

public class UserContactUrls implements UrlSpace {

    public static final String CONTACT = "/contact";
    public static final String CONTACTS = "/contacts";

    public class Api {
        public static final String CONTACT = "/api"  + UserContactUrls.CONTACT;
        public static final String CONTACTS = "/api"  + UserContactUrls.CONTACTS;

        public static final String USER_CONTACT_DELETE = "/delete";
        public static final String USER_CONTACT_DELETE_FULL = Api.CONTACT + Api.USER_CONTACT_DELETE;
        public static final String USER_CONTACT_DELETE_ID = USER_CONTACT_DELETE + "/{personInBook}";

    }
}
