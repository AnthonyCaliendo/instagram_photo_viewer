package com.anthonycaliendo.instagramphotoviewer.model.dao;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;

import java.util.List;

/**
 * Records the data passed into the callbacks.
 */
class RecordingResponseHandler implements InstagramClient.ResponseHandler {
    public List<Photo> photos;
    public boolean isFailed;

    public RecordingResponseHandler() {
        isFailed = false;
    }

    @Override
    public void onSuccess(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public void onFail() {
        this.isFailed = true;
    }
}
