package com.anthonycaliendo.instagramphotoviewer.util;

import android.test.ActivityInstrumentationTestCase2;

import com.anthonycaliendo.instagramphotoviewer.ViewPopularStreamActivity;

public class ConfigurationTest extends ActivityInstrumentationTestCase2<ViewPopularStreamActivity> {

    public ConfigurationTest() {
        super(ViewPopularStreamActivity.class);
    }

    public void testGetInstagramClientId_LoadsFromPropertyFile() {
        assertNotNull(
                "should retrieve a client id",
                Configuration.getInstagramClientId(getActivity().getAssets())
        );
    }
}
