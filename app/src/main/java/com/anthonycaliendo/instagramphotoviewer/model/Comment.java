package com.anthonycaliendo.instagramphotoviewer.model;

/**
 * Contains information about a comment made on media.
 */
public class Comment {

    /**
     * The text of the comment.
     */
    private final String text;

    /**
     * The user who made the comment.
     */
    private final User commenter;

    public Comment(final String text, final User commenter) {
        this.text      = text;
        this.commenter = commenter;
    }

    public String getText() {
        return text;
    }

    public String getCommenterUsername() {
        if (commenter == null) {
            return null;
        }
        return commenter.getUsername();
    }

    public String getCommenterProfileImageUrl() {
        if (commenter == null) {
            return null;
        }
        return commenter.getProfileImageUrl();
    }
}
