package com.anthonycaliendo.instagramphotoviewer.model;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single instagram Photo.
 */
public class Photo {

    /**
     * The caption for this photo.
     */
    private String caption;

    /**
     * The user who posted this photo.
     */
    private User poster;

    /**
     * The url for the image for this photo.
     */
    private String imageUrl;

    /**
     * The time this photo was posted at.
     */
    private Calendar postedAt;

    /**
     * The number of times this photo has been "liked".
     */
    private Integer likeCount;

    /**
     * The number of comments this photo has received.
     */
    private Integer commentCount;

    /**
     * The comments made on this photo.
     */
    private List<Comment> comments;

    public Photo(final String caption, final User poster, final String imageUrl, final Calendar postedAt, final Integer likeCount, final Integer commentCount, final List<Comment> comments) {
        this.caption   = caption;
        this.poster    = poster;
        this.imageUrl  = imageUrl;
        this.postedAt  = postedAt;

        if (comments == null) {
            this.comments = Collections.emptyList();
        } else {
            this.comments = comments;
        }

        if(likeCount == null) {
            this.likeCount = 0;
        } else {
            this.likeCount = likeCount;
        }

        if(commentCount == null) {
            this.commentCount = 0;
        } else {
            this.commentCount = commentCount;
        }
    }

    public String getCaption() {
        return caption;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Calendar getPostedAt() {
        return postedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPosterUsername() {
        if (poster == null) {
            return null;
        }
        return poster.getUsername();
    }

    public String getPosterFullName() {
        if (poster == null) {
            return null;
        }
        return poster.getFullName();
    }

    public String getPosterProfileImageUrl() {
        if (poster == null) {
            return null;
        }
        return poster.getProfileImageUrl();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
