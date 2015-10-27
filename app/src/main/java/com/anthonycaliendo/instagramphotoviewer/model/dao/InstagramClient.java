package com.anthonycaliendo.instagramphotoviewer.model.dao;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.util.Configuration;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

/**
 * Makes remote calls to instagram and returns data.
 */
public class InstagramClient {

    /**
     * Async callback to handle Instagram responses.
     */
    public interface ResponseHandler {
        /**
         * Callback method invoked on a successful response.
         *
         * @param photos
         *      the list of photos being returned
         */
        void onSuccess(List<Photo> photos);

        /**
         * Callback method invoked when unable to retrieve a response for any reason.
         */
        void onFail();
    }

    /**
     * Used to make HTTP calls.
     */
    private final AsyncHttpClient asyncHttpClient;

    /**
     * Used to parse the JSON responses.
     */
    private final InstagramResponseParser responseParser;

    /**
     * The clientId to use to make requests.
     */
    private final String clientId;

    /**
     * Instantiates an Instagram client which can make calls to instagram.
     * Requests are made asynchronously, and will invoke callbacks when they complete.
     *  @param asyncHttpClient
     *      the {@link AsyncHttpClient} to use to make requests
     *
     */
    public InstagramClient(final AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
        this.responseParser  = new InstagramResponseParser();
        this.clientId        = Configuration.getInstagramClientId();
    }

    /**
     * Make a call to the popular photo endpoint in instagram and parse the response.
     *
     * @param responseHandler
     *      the callbacks to handle the response
     */
    public void fetchPopularPhotos(final ResponseHandler responseHandler) {
        final RequestParams requestParams = new RequestParams();
        requestParams.put("client_id", this.clientId);

        try {
            asyncHttpClient.get(Configuration.getInstagramPopularMediaUrl(), requestParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    debug(this, "method=fetchPopularPhotos handler=onSuccess statusCode=" + statusCode + " response=" + response.toString());
                    try {
                        responseHandler.onSuccess(responseParser.parseMultiPhotoResponse(response));
                    } catch (final Exception e) {
                        debug(this, "method=fetchPopularPhotos handler=onSuccess", e);
                        responseHandler.onFail();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    debug(this, "method=fetchPopularPhotos handler=onFailure statusCode=" + statusCode);
                    responseHandler.onFail();
                }
            });
        } catch (final Exception e){
            debug(this, "method=fetchPopularPhotos", e);
            responseHandler.onFail();
        }
    }

}
