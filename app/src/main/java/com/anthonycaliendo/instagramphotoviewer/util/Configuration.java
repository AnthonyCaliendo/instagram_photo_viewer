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
     * @param assetManager
     *      the asset manager to use to load the properties file
     * @return
     *      the client id used to make Instagram requests
     */
    public static String getInstagramClientId(final AssetManager assetManager) {
        final Properties properties = new Properties();
        InputStream configStream = null;

        try {
            configStream = assetManager.open("clients.properties");
            properties.load(configStream);
            debug(Configuration.class, "properties=" + properties.toString());
            configStream.close();
        }  catch (FileNotFoundException e) {
            debug(Configuration.class, "method=getInstagramClientid section=propertyLoad", e);
        } catch (IOException e) {
            debug(Configuration.class, "method=getInstagramClientid section=propertyLoad", e);
        } finally {
            if (configStream != null) {
                try {
                    configStream.close();
                } catch (IOException e) {
                    debug(Configuration.class, "method=getInstagramClientid section=finallyBlock", e);
                }
            }
        }
        return properties.getProperty("instagramClientId");
    }
}
