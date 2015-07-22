package com.yuliu.util;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Shared class used by every sample. Contains methods for authorizing a user
 * and caching credentials.
 */
public class Auth {

    public static final String apiKey = "AIzaSyAFIQYr87ul8NfqaF4Jw7YHJ7tT4MlAQbw";
    public static final String appName = "music video";
    /**
     * Define a global instance of the HTTP transport.
     */
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Define a global instance of the JSON factory.
     */
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

}
