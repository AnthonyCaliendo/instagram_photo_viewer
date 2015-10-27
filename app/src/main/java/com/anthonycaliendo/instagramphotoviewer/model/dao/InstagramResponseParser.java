package com.anthonycaliendo.instagramphotoviewer.model.dao;

import com.anthonycaliendo.instagramphotoviewer.model.Comment;
import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.model.User;
import com.anthonycaliendo.instagramphotoviewer.util.Instrumentation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

/**
 * Parses responses from Instagram into model objects.
 */
class InstagramResponseParser {

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
    public List<Photo> parseMultiPhotoResponse(final JSONObject response) throws JSONException {
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
