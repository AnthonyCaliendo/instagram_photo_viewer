package com.anthonycaliendo.instagramphotoviewer.model;

/**
 * Represents a User who comments or provides captions for media.
 */
public class User {

    /**
     * The username for this user.
     */
    private String username;

    /**
     * The full name for this user.
     */
    private String fullName;

    /**
     * The url for the profile picture for this user.
     */
    private String profileImageUrl;

    public User(final String username, final String fullName, final String profileImageUrl) {
        this.username        = username;
        this.fullName        = fullName;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
