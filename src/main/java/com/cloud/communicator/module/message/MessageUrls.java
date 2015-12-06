package com.cloud.communicator.module.message;

import com.cloud.communicator.api.UrlSpace;

public class MessageUrls implements UrlSpace {
    public static final String MESSAGE = "/message";
    public static final String MESSAGES = "/messages";

    public class Api {
        public static final String MESSAGE = "/api"  + MessageUrls.MESSAGE;

        public static final String MESSAGES = "/api" + MessageUrls.MESSAGES;

        public static final String MESSAGE_CHANGE_READ_STATUS = "/status";
        public static final String MESSAGE_CHANGE_READ_STATUS_FULL = Api.MESSAGE + Api.MESSAGE_CHANGE_READ_STATUS;
        public static final String MESSAGE_CHANGE_READ_STATUS_ID = MESSAGE_CHANGE_READ_STATUS + "/{messageId}";

        public static final String MESSAGE_DELETE = "/delete";
        public static final String MESSAGE_DELETE_FULL = Api.MESSAGE + Api.MESSAGE_DELETE;
        public static final String MESSAGE_DELETE_ID = MESSAGE_DELETE + "/{messageId}";

    }
}
