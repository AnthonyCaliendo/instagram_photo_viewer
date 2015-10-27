package com.anthonycaliendo.instagramphotoviewer.model.dao;

import java.util.Calendar;

/**
 * Used to hold JSON responses used for testing.
 * Declutters the tests.
 */
abstract class TestJsonResponses {

    static final Calendar FIRST_PHOTO_CREATED_AT = Calendar. getInstance();
    static {
        FIRST_PHOTO_CREATED_AT.setTimeInMillis(1445805000);
    }

    static final Calendar SECOND_PHOTO_CREATED_AT = Calendar.getInstance();
    static {
        SECOND_PHOTO_CREATED_AT.setTimeInMillis(1245805000);
    }

    static final String FULLY_POPULATED_IMAGE_ONLY_JSON_RESPONSE = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \n" +
            "      \"comments\": {\n" +
            "        \"count\": 111,\n" +
            "        \"data\": [\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment1\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter1\",\n" +
            "              \"profile_picture\": \"http://example.com/photo1-commenter1-profile.jpg\"" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"text\": \"photo1-comment2\",\n" +
            "            \"from\": {\n" +
            "              \"username\": \"photo1-commenter2\",\n" +
            "              \"profile_picture\": \"http://example.com/photo1-commenter2-profile.jpg\"" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"" + convertCalendarToInstagramTimestamp(FIRST_PHOTO_CREATED_AT) + "\",\n" +
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
            "              \"username\": \"photo2-commenter1\",\n" +
            "              \"profile_picture\": \"http://example.com/photo2-commenter1-profile.jpg\"" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"created_time\": \"" + convertCalendarToInstagramTimestamp(SECOND_PHOTO_CREATED_AT) + "\",\n" +
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

    static final String FULLY_POPULATED_MULTI_TYPE_RESPONSE_JSON = "{\n" +
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
            "      \"created_time\": \"" + convertCalendarToInstagramTimestamp(FIRST_PHOTO_CREATED_AT) + "\",\n" +
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
            "      \"created_time\": \"" + convertCalendarToInstagramTimestamp(SECOND_PHOTO_CREATED_AT) + "\",\n" +
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
     * Convert a calendar into a timestamp used by instagram.
     * @param calendar
     *      the calendar to convert
     * @return
     *      the converted timestamp
     */
    private static String convertCalendarToInstagramTimestamp(final Calendar calendar) {
        return Integer.valueOf((int)(calendar.getTimeInMillis() / 1000)).toString();
    }
}
