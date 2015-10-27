package com.anthonycaliendo.instagramphotoviewer;

import android.app.Application;
import android.test.ApplicationTestCase;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testApplicationCanBeCreated() {
        createApplication();
        assertNotNull("should be able to load and create application", getApplication());
    }

}