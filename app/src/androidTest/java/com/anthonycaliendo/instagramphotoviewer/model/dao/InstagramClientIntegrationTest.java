package com.anthonycaliendo.instagramphotoviewer.model.dao;

import android.os.Looper;
import android.test.AndroidTestCase;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.util.Configuration;
import com.loopj.android.http.AsyncHttpClient;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InstagramClientIntegrationTest extends AndroidTestCase {

    public void testFetchPopularPhotos_LoadsDataFromInstagram() throws Exception {
        class RecordingResponseHandler implements InstagramClient.ResponseHandler {
            public List<Photo> photos = null;
            @Override
            public void onSuccess(List<Photo> photos) {
                this.photos = photos;
            }
        }

        final InstagramClient client                   = new InstagramClient(new AsyncHttpClient(), Configuration.getInstagramClientId(getContext().getAssets()));
        final RecordingResponseHandler responseHandler = new RecordingResponseHandler();
        final ExecutorService executor                 = Executors.newFixedThreadPool(2);
        final Future<List<Photo>> instagramCallFuture  = executor.submit(new Callable<List<Photo>>() {
            @Override
            public List<Photo> call() throws Exception {
                Looper.prepare();
                client.fetchPopularPhotos(responseHandler);
                while (responseHandler.photos == null) {
                    // block until we get photos
                }
                return responseHandler.photos;
            }
        });

        // give the test 10 seconds to load the data
        final List<Photo> photos = instagramCallFuture.get(10, TimeUnit.SECONDS);

        assertFalse("should have photos", photos.isEmpty());
        // all photos should have at least a username and posting timestamp. everything else is non-deterministic should we shouldn't assert against
        assertNotNull("should have a poster username", photos.get(0).getPosterUsername());
        assertNotNull("should have a posted at", photos.get(0).getPostedAt());
    }
}
