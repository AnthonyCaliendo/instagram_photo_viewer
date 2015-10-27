package com.anthonycaliendo.instagramphotoviewer.util;

import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

/**
 * Provides configuration access and support.
 */
public class Configuration {

    /**
     * Retrieves the instagram client id from configuration. Expects that there is a file called
     * "clients.properties" with a key "instagramClientId".
     * @return
     *      the client id used to make Instagram requests
     */
    public static String getInstagramClientId() {
        throw new RuntimeException("please enter a client id in " + Configuration.class.getName() + ".'");
    }

    /**
     * The url used to fetch popular media (photos and video) from Instagram.
     *
     * @return
     *      the popular media url
     */
    public static String getInstagramPopularMediaUrl() {
        return "https://api.instagram.com/v1/media/popular";
    }
}
