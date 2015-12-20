package com.cloud.communicator.module.message;

import com.cloud.communicator.api.UrlSpace;

public class FolderUrls implements UrlSpace {

    public static final String FOLDER = "/folder";
    public static final String FOLDERS = "/folders";

    public class Api {
        public static final String FOLDER = "/api"  + FolderUrls.FOLDER;

        public static final String FOLDERS = "/api" + FolderUrls.FOLDERS;
    }
}
