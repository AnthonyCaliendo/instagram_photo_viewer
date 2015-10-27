package com.anthonycaliendo.instagramphotoviewer.model.dao;

import com.anthonycaliendo.instagramphotoviewer.model.Comment;
import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.model.User;
import com.anthonycaliendo.instagramphotoviewer.util.Configuration;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

/**
 * Makes remote calls to instagram and returns data.
 */
public class InstagramClient {

    /**
     * The url used to fetch popular photos.
     */
    private static final String POPULAR_PHOTOS_URL = "https://api.instagram.com/v1/media/popular";

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
    }

    /**
     * Used to make HTTP calls.
     */
    private final AsyncHttpClient asyncHttpClient;

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
            asyncHttpClient.get(POPULAR_PHOTOS_URL, requestParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    debug(this, "method=fetchPopularPhotos handler=onSuccess statusCode=" + statusCode + " response=" + response.toString());
                    try {
                        responseHandler.onSuccess(parseMultiPhotoResponse(response));
                    } catch (final Exception e) {
                        debug(this, "method=fetchPopularPhotos handler=onSuccess", e);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    debug(this, "method=fetchPopularPhotos handler=onFailure statusCode=" + statusCode);
                }
            });
        } catch (final Exception e){
            debug(this, "method=fetchPopularPhotos", e);
        }
    }

    /**
     * Parses the response into a list of {@link Photo} objects.
     *
     * @param response
     *      the json response
     * @return
     *      the parsed list of {@link Photo} objects
     * @throws JSONException
     *      if the json is invalid
     */
    private List<Photo> parseMultiPhotoResponse(final JSONObject response) throws JSONException {
        final List<Photo> photos= new ArrayList<>();

        if (response.isNull("data")) {
            return photos;
        }

        final JSONArray mediaData = response.getJSONArray("data");
        debug(this, "method=parseMultiPhotoResponse inputSize=" + mediaData.length());

        for (int mediaIndex = 0; mediaIndex < mediaData.length() ; mediaIndex++) {
            final JSONObject mediaObject = mediaData.optJSONObject(mediaIndex);
            if (mediaObject == null || !"image".equals(mediaObject.optString("type"))) {
                continue;
            }
            photos.add(parsePhoto(mediaObject));
        }

        debug(this, "method=parseMultiPhotoResponse ouputSize=" + photos.size());
        return photos;
    }

    /**
     * Parses the json object as a photo. Expects that the passed {@code JSONObject} represents an
     * image; behavior is undefined if this is not the case.
     *
     * @param jsonObject
     *      the photo json object to parse
     * @return
     *      the {@link Photo} object with values set from the passed json object
     */
    private Photo parsePhoto(final JSONObject jsonObject) {
        final String caption         = parseString(jsonObject, "caption", "text");
        final User poster            = parseUser(jsonObject.optJSONObject("user"));
        final String imageUrl        = parseString(jsonObject, "images", "standard_resolution", "url");
        final Calendar postedAt      = parseTimestamp(jsonObject.optString("created_time"));
        final Integer likeCount      = parseInteger(parseString(jsonObject, "likes", "count"));
        final Integer commentCount   = parseInteger(parseString(jsonObject, "comments", "count"));
        final List<Comment> comments = parseComments(jsonObject.optJSONObject("comments"));

        return new Photo(caption, poster, imageUrl, postedAt, likeCount, commentCount, comments);
    }

    /**
     * Parses comments out of the json object, and returns them as a list.
     * @param jsonObject
     *      the json object to parse comments from
     * @return
     *      a list of parsed comments
     */
    private List<Comment> parseComments(final JSONObject jsonObject) {
        final ArrayList<Comment> parsedComments = new ArrayList<>();

        if (jsonObject != null) {

            final JSONArray commentsJsonArray = jsonObject.optJSONArray("data");
            if (commentsJsonArray != null) {

                for (int commentIndex = 0; commentIndex < commentsJsonArray.length(); commentIndex++) {
                    final JSONObject commentJsonObject = commentsJsonArray.optJSONObject(commentIndex);

                    if (commentJsonObject == null) {
                        continue;
                    }

                    parsedComments.add(new Comment(
                            commentJsonObject.optString("text"),
                            parseUser(commentJsonObject.optJSONObject("from"))
                    ));
                }
            }
        }

        debug(this, "method=parseComments outputSize=" + parsedComments.size());

        return parsedComments;
    }

    /**
     * Parses a {@link User} object from the passed json. Expects that the passed {@code JSONObject}
     * represents an image; behavior is undefined if this is not the case.
     *
     * @param jsonObject
     *      the user json object to parse
     * @return
     *      the {@link User} object with values set from the passed json object
     */
    private User parseUser(final JSONObject jsonObject) {
        return new User(
                parseString(jsonObject, "username"),
                parseString(jsonObject, "full_name"),
                parseString(jsonObject, "profile_picture")
        );
    }

    /**
     * Safely parse a nested string value in a {@link JSONObject}. Will return null if the name does
     * not exist at any level.
     *
     * @param jsonObject
     *      the root json object
     * @param names
     *      the names of the nested values to parse
     * @return
     *      the nested string value, or null if it cannot be found
     */
    private String parseString(JSONObject jsonObject, final String... names) {
        for (int nameIndex = 0; nameIndex < (names.length - 1); nameIndex++) {
            if (jsonObject == null) {
                break;
            }
            jsonObject = jsonObject.optJSONObject(names[nameIndex]);
        }

        if (jsonObject == null) {
            return null;
        }

        return jsonObject.optString(names[names.length - 1], null);
    }

    /**
     * Safely parse the integer value for the string. Will return null if the value cannot be parsed.
     *
     * @param value
     *      the value to parse
     * @return
     *      the Integer value of the string, or null if it cannot be parsed.
     */
    private Integer parseInteger(final String value) {
        if (value == null) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (final NumberFormatException e) {
            debug(this, "method=parseInteger", e);
            return null;
        }
    }

    /**
     * Converts a timestamp into a {@link Calendar} object.
     *
     * @param timestamp
     *      the timestamp as a String
     * @return
     *      the parsed {@link Calendar}, or null if null is passed
     */
    private Calendar parseTimestamp(final String timestamp){
        if (timestamp == null || timestamp == "") {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
        return calendar;
    }

}
