package com.anthonycaliendo.instagramphotoviewer.model;

import android.test.AndroidTestCase;

public class PhotoTest extends AndroidTestCase {

    public void testGetPosterUsername_NullPoster_ReturnsNull() {
        final Photo photo = new Photo(null, null, null, null, null, null, null);

        assertNull("should have null poster username", photo.getPosterUsername());
    }

    public void testGetPosterFullName_NullPoster_ReturnsNull() {
        final Photo photo = new Photo(null, null, null, null, null, null, null);

        assertNull("should have null poster full name", photo.getPosterFullName());
    }

    public void testGetPosterProfileImage_NullPoster_ReturnsNull() {
        final Photo photo = new Photo(null, null, null, null, null, null, null);

        assertNull("should have null poster profile image url", photo.getPosterProfileImageUrl());
    }
}
