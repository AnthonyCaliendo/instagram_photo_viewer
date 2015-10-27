package com.anthonycaliendo.instagramphotoviewer.model.dao;

import android.test.AndroidTestCase;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class InstagramClientTest extends AndroidTestCase {

    private static final String CLIENT_ID = null;

    private static final Calendar firstCreatedAt = Calendar.getInstance();
    static {
        firstCreatedAt.setTimeInMillis(1445805000);
    }

    private static final Calendar secondCreatedAt = Calendar.getInstance();
    static {
        secondCreatedAt.setTimeInMillis(1245805000);
    }
    private static final String FULLY_POPULATED_IMAGE_ONLY_JSON_RESPONSE = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \n" +
            "      \"comments\": {\n" +
            "        \"count\": 111,\n" +
            "        \"data\": [\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment1\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter1\"\n" +
            "              \"profile_picture\": \"http://photo1-commenter1-profile.jpg\"," +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment2\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter2\"\n" +
            "              \"profile_picture\": \"http://photo1-commenter2-profile.jpg\"," +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"1445805\",\n" +
            "      \"likes\": {\n" +
            "        \"count\": 222\n" +
            "      },\n" +
            "      \"images\": {\n" +
            "        \"standard_resolution\": {\n" +
            "          \"url\": \"http://example.com/photo1-highres.jpg\",\n" +
            "          \"width\": 640,\n" +
            "          \"height\": 640\n" +
            "        }\n" +
            "      },\n" +
            "      \"caption\": {\n" +
            "        \"text\": \"photo1-caption\"\n" +
            "      },\n" +
            "      \"type\": \"image\",\n" +
            "      \"user\": {\n" +
            "        \"username\": \"photo1-username\",\n" +
            "        \"profile_picture\": \"http://example.com/photo1-profile.jpg\",\n" +
            "        \"full_name\": \"photo1-full_name\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \n" +
            "      \"comments\": {\n" +
            "        \"count\": 333,\n" +
            "        \"data\": [\n" +
            "          {\n" +
            "            \"text\": \"photo2-comment1\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo2-commenter1\"\n" +
            "              \"profile_picture\": \"http://photo2-commenter1-profile.jpg\"," +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"1245805\",\n" +
            "      \"likes\": {\n" +
            "        \"count\": 444\n" +
            "      },\n" +
            "      \"images\": {\n" +
            "        \"standard_resolution\": {\n" +
            "          \"url\": \"http://example.com/photo2-highres.jpg\",\n" +
            "          \"width\": 640,\n" +
            "          \"height\": 640\n" +
            "        }\n" +
            "      },\n" +
            "      \"caption\": {\n" +
            "        \"text\": \"photo2-caption\"\n" +
            "      },\n" +
            "      \"type\": \"image\",\n" +
            "      \"user\": {\n" +
            "        \"username\": \"photo2-username\",\n" +
            "        \"profile_picture\": \"http://example.com/photo2-profile.jpg\",\n" +
            "        \"full_name\": \"photo2-full_name\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static final String FULLY_POPULATED_MULTI_TYPE_RESPONSE_JSON = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \n" +
            "      \"comments\": {\n" +
            "        \"count\": 111,\n" +
            "        \"data\": [\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment1\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter1\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment2\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter2\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"1445805\",\n" +
            "      \"likes\": {\n" +
            "        \"count\": 222\n" +
            "      },\n" +
            "      \"images\": {\n" +
            "        \"standard_resolution\": {\n" +
            "          \"url\": \"http://example.com/photo1-highres.jpg\",\n" +
            "          \"width\": 640,\n" +
            "          \"height\": 640\n" +
            "        }\n" +
            "      },\n" +
            "      \"caption\": {\n" +
            "        \"text\": \"photo1-caption\"\n" +
            "      },\n" +
            "      \"type\": \"image\",\n" +
            "      \"user\": {\n" +
            "        \"username\": \"photo1-username\",\n" +
            "        \"profile_picture\": \"http://example.com/photo1-profile.jpg\",\n" +
            "        \"full_name\": \"photo1-full_name\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \n" +
            "      \"comments\": {\n" +
            "        \"count\": 333,\n" +
            "        \"data\": [\n" +
            "          {\n" +
            "            \"text\": \"photo2-comment1\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo2-commenter1\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"1245805\",\n" +
            "      \"likes\": {\n" +
            "        \"count\": 444\n" +
            "      },\n" +
            "      \"images\": {\n" +
            "        \"standard_resolution\": {\n" +
            "          \"url\": \"http://example.com/photo2-highres.jpg\",\n" +
            "          \"width\": 640,\n" +
            "          \"height\": 640\n" +
            "        }\n" +
            "      },\n" +
            "      \"caption\": {\n" +
            "        \"text\": \"photo2-caption\"\n" +
            "      },\n" +
            "      \"type\": \"video\",\n" +
            "      \"user\": {\n" +
            "        \"username\": \"photo2-username\",\n" +
            "        \"profile_picture\": \"http://example.com/photo2-profile.jpg\",\n" +
            "        \"full_name\": \"photo2-full_name\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    /**
     * Used to record responses passed into the handlers.
     */
    private class RecordingResponseHandler implements InstagramClient.ResponseHandler {
        public List<Photo> photos;
        public boolean isFailed = false;

        @Override
        public void onSuccess(List<Photo> photos) {
            this.photos = photos;
        }

        @Override
        public void onFail() {
            this.isFailed = true;
        }
    }

    /**
     * Creates an {@link InstagramClient} with a stub {@link AsyncHttpClient} which will immediately
     * callback with a successful response using the specified payload.
     *
     * @param jsonResponse
     *      the json payload to use as a response
     * @return
     */
    private List<Photo> fetchPopularPhotos(final String jsonResponse) {
        final AsyncHttpClient stubbedAsyncClient = new AsyncHttpClient() {
            @Override
            public RequestHandle get(final String url, final RequestParams requestParams, final ResponseHandlerInterface responseHandler) {
                final JsonHttpResponseHandler jsonResponseHandler = (JsonHttpResponseHandler) responseHandler;
                try {
                    jsonResponseHandler.onSuccess(200, null, new JSONObject(jsonResponse));
                } catch (final JSONException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };

        final InstagramClient instagramClient = new InstagramClient(stubbedAsyncClient);

        final RecordingResponseHandler responseHandler = new RecordingResponseHandler();
        instagramClient.fetchPopularPhotos(responseHandler);

        assertFalse("response should not have failed", responseHandler.isFailed);
        assertNotNull("photos should not be null", responseHandler.photos);

        return responseHandler.photos;
    }

    public void testFetchPopularPhotos_ParsesJsonResponse() {
        final List<Photo> photos = fetchPopularPhotos(FULLY_POPULATED_IMAGE_ONLY_JSON_RESPONSE);

        assertEquals("should have 2 photos", 2, photos.size());

        final Photo firstPhoto = photos.get(0);
        assertEquals("should have the correct caption", "photo1-caption", firstPhoto.getCaption());
        assertEquals("should have correct posted at", firstCreatedAt, firstPhoto.getPostedAt());
        assertEquals("should have correct like count", 222, firstPhoto.getLikeCount().intValue());
        assertEquals("should have correct image url", "http://example.com/photo1-highres.jpg", firstPhoto.getImageUrl());
        assertEquals("should have the correct poster username", "photo1-username", firstPhoto.getPosterUsername());
        assertEquals("should have the correct poster name", "photo1-full_name", firstPhoto.getPosterFullName());
        assertEquals("should have the correct poster profile image url", "http://example.com/photo1-profile.jpg", firstPhoto.getPosterProfileImageUrl());
        assertEquals("should have correct comment count", 111, firstPhoto.getCommentCount().intValue());

        assertEquals("should have 2 comments", 2, firstPhoto.getComments().size());
        assertEquals("should have the correct comment text", "photo1-comment1", firstPhoto.getComments().get(0).getText());
        assertEquals("should have the correct commenter username", "photo1-commenter1", firstPhoto.getComments().get(0).getCommenterUsername());
        assertEquals("should have the correct commenter profile image url", "http://example.com/photo1-commenter1-profile.jpg", firstPhoto.getComments().get(0).getCommenterProfileImageUrl());
        assertEquals("should have the correct comment text", "photo1-comment2", firstPhoto.getComments().get(1).getText());
        assertEquals("should have the correct commenter username", "photo1-commenter2", firstPhoto.getComments().get(1).getCommenterUsername());
        assertEquals("should have the correct commenter profile image url", "http://example.com/photo1-commenter2-profile.jpg", firstPhoto.getComments().get(1).getCommenterProfileImageUrl());

        final Photo secondPhoto = photos.get(1);
        assertEquals("should have the correct caption", "photo2-caption", secondPhoto.getCaption());
        assertEquals("should have correct posted at", secondCreatedAt, secondPhoto.getPostedAt());
        assertEquals("should have correct like count", 444, secondPhoto.getLikeCount().intValue());
        assertEquals("should have correct image url", "http://example.com/photo2-highres.jpg", secondPhoto.getImageUrl());
        assertEquals("should have the correct poster username", "photo2-username", secondPhoto.getPosterUsername());
        assertEquals("should have the correct poster name", "photo2-full_name", secondPhoto.getPosterFullName());
        assertEquals("should have the correct poster profile image url", "http://example.com/photo2-profile.jpg", secondPhoto.getPosterProfileImageUrl());
        assertEquals("should have correct comment count", 333, secondPhoto.getCommentCount().intValue());

        assertEquals("should have 1 comment", 1, secondPhoto.getComments().size());
        assertEquals("should have the correct comment text", "photo2-comment1", secondPhoto.getComments().get(0).getText());
        assertEquals("should have the correct commenter username", "photo2-commenter1", secondPhoto.getComments().get(0).getCommenterUsername());
        assertEquals("should have the correct commenter profile image url", "http://example.com/photo2-commenter1-profile.jpg", secondPhoto.getComments().get(0).getCommenterProfileImageUrl());
    }

    public void testFetchPopularPhotos_ExceptionThrownInRemoter_DoesNotInvokeSucessCallbackAndDoesNotRaiseException() {
        final InstagramClient instagramClient = new InstagramClient(new AsyncHttpClient() {
            @Override
            public RequestHandle get(final String url, final ResponseHandlerInterface responseHandler) {
                throw new RuntimeException("the roof");
            }
        });

        final RecordingResponseHandler responseHandler = new RecordingResponseHandler() {
            @Override
            public void onSuccess(List<Photo> photos) {
                fail("should not invoke the success callback");
            }
        };

        instagramClient.fetchPopularPhotos(responseHandler);

        assertTrue("should invoke onFail handler", responseHandler.isFailed);
    }

    public void testFetchPopularPhotos_NoDataBlock_ReturnsEmptyList() {
        final List<Photo> photos = fetchPopularPhotos("{}");

        assertTrue("should return empty list", photos.isEmpty());
    }

    public void testFetchPopularPhotos_IgnoresNonImageMediaTypes() {
        final List<Photo> photos = fetchPopularPhotos(FULLY_POPULATED_MULTI_TYPE_RESPONSE_JSON);

        assertEquals("should have 1 photo", 1, photos.size());

        final Photo firstPhoto = photos.get(0);
        assertEquals("should have the correct caption", "photo1-caption", firstPhoto.getCaption());
        assertEquals("should have correct posted at", firstCreatedAt, firstPhoto.getPostedAt());
        assertEquals("should have correct like count", 222, firstPhoto.getLikeCount().intValue());
        assertEquals("should have correct image url", "http://example.com/photo1-highres.jpg", firstPhoto.getImageUrl());
        assertEquals("should have the correct poster username", "photo1-username", firstPhoto.getPosterUsername());
        assertEquals("should have the correct poster name", "photo1-full_name", firstPhoto.getPosterFullName());
        assertEquals("should have the correct poster profile image url", "http://example.com/photo1-profile.jpg", firstPhoto.getPosterProfileImageUrl());
        assertEquals("should have correct comment count", 111, firstPhoto.getCommentCount().intValue());

    }

    public void testFetchPopularPhotos_MissingJsonElements_SetsNullValues() {
        final List<Photo> photos = fetchPopularPhotos("{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"type\": \"image\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"image\",\n" +
                "      \"caption\": {},\n" +
                "      \"comments\": {},\n" +
                "      \"likes\": {},\n" +
                "      \"user\": {},\n" +
                "      \"images\": {}\n" +
                "    }\n" +
                "  ]\n" +
                "}");

        assertEquals("should have 2 photo", 2, photos.size());

        final Photo firstPhoto = photos.get(0);
        assertNull("should have null caption", firstPhoto.getCaption());
        assertNull("should have no posted at", firstPhoto.getPostedAt());
        assertEquals("should have zero like count", 0, firstPhoto.getLikeCount().intValue());
        assertNull("should have no image url", firstPhoto.getImageUrl());
        assertNull("should have no poster username", firstPhoto.getPosterUsername());
        assertNull("should have no poster name", firstPhoto.getPosterFullName());
        assertNull("should have no poster profile image url", firstPhoto.getPosterProfileImageUrl());
        assertEquals("should have correct comment count", 0, firstPhoto.getCommentCount().intValue());

        final Photo secondPhoto = photos.get(1);
        assertNull("should have null caption", secondPhoto.getCaption());
        assertNull("should have no posted at", secondPhoto.getPostedAt());
        assertEquals("should have zero like count", 0, secondPhoto.getLikeCount().intValue());
        assertNull("should have no image url", secondPhoto.getImageUrl());
        assertNull("should have no poster username", secondPhoto.getPosterUsername());
        assertNull("should have no poster name", secondPhoto.getPosterFullName());
        assertNull("should have no poster profile image url", secondPhoto.getPosterProfileImageUrl());
        assertEquals("should have correct comment count", 0, secondPhoto.getCommentCount().intValue());

    }

    public void testFetchPopularPhotos_InvokesCorrectHttpRequest() {
        class RecordingAsyncHttpClient extends AsyncHttpClient {
            public String              invokedUrl;
            public Map<String, String> requestParams;

            @Override
            public RequestHandle get(final String url, final RequestParams requestParams, final ResponseHandlerInterface responseHandler) {
                this.invokedUrl    = url;

                // {@link RequestParams} is designed poorly, so we need to use reflection to grab what is inside it.
                final Field field;
                try {
                    field = requestParams.getClass().getDeclaredField("urlParams");
                    field.setAccessible(true);
                    this.requestParams = (Map<String, String>) field.get(requestParams);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }

        final RecordingAsyncHttpClient asyncHttpClient = new RecordingAsyncHttpClient();
        final InstagramClient instagramClient  = new InstagramClient(asyncHttpClient);

        instagramClient.fetchPopularPhotos(new RecordingResponseHandler());

        assertEquals("should pass correct url into remoter", "https://api.instagram.com/v1/media/popular", asyncHttpClient.invokedUrl);
        assertEquals("should pass correct client_id", "thisIsMyClientId", asyncHttpClient.requestParams.get("client_id"));
    }
}
