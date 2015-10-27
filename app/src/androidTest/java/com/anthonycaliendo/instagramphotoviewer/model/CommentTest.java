package com.anthonycaliendo.instagramphotoviewer.model;

import android.test.AndroidTestCase;

public class CommentTest extends AndroidTestCase {

    public void testGetCommenterUsername_NullCommenter_ReturnsNull() {
        final Comment comment = new Comment(null, null);

        assertNull("should have null commenter username", comment.getCommenterUsername());
    }

    public void testGetCommenterProfileImageUrl_NullCommenter_ReturnsNull() {
        final Comment comment = new Comment(null, null);

        assertNull("should have null commenter username", comment.getCommenterProfileImageUrl());
    }
}