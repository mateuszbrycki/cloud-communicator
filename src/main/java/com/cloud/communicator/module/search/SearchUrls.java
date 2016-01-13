package com.cloud.communicator.module.search;

/**
 * Created by Mateusz on 13.01.2016.
 */
public class SearchUrls {

    public static final String SEARCH = "/search";

    public class Api {
        public static final String SEARCH = "/api"  + SearchUrls.SEARCH;

        public static final String SEARCH_PHRASE = Api.SEARCH  + "/{searchPhrase}";

    }

}
