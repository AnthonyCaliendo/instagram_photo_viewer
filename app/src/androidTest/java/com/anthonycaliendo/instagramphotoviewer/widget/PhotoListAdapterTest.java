package com.anthonycaliendo.instagramphotoviewer.widget;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.TextView;

import com.anthonycaliendo.instagramphotoviewer.R;
import com.anthonycaliendo.instagramphotoviewer.ViewPopularStreamActivity;
import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.model.User;

import java.util.Arrays;
import java.util.Calendar;

public class PhotoListAdapterTest extends ActivityInstrumentationTestCase2<ViewPopularStreamActivity> {

    public PhotoListAdapterTest() {
        super(ViewPopularStreamActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
    }

    private PhotoListAdapter createAdapter(final Photo ... photos) {
        return new PhotoListAdapter(getInstrumentation().getTargetContext(), Arrays.asList(photos));
    }

    @UiThreadTest
    public void testGetView_SetsTextValues() {
        final Calendar postedAt = Calendar.getInstance();
        postedAt.set(2015, 10, 26, 22, 00, 00);
        final User poster              = new User("username", "full name", "profileImageUrl");
        final Photo photo              = new Photo("caption", poster, "imageUrl", postedAt, 7, null, null);
        final PhotoListAdapter adapter = createAdapter(photo);

        final View photoView          = adapter.getView(0, null, null);
        final TextView caption        = (TextView) photoView.findViewById(R.id.item_photo_caption);
        final TextView posterUsername = (TextView) photoView.findViewById(R.id.item_photo_poster_username);
        final TextView posterFullName = (TextView) photoView.findViewById(R.id.item_photo_poster_full_name);
        final TextView likeCount      = (TextView) photoView.findViewById(R.id.item_photo_like_count);

        assertEquals("should set caption", "caption", caption.getText());
        assertEquals("should set poster username", "username", posterUsername.getText());
        assertEquals("should set poster full name", "full name", posterFullName.getText());
        assertEquals("should set like count", "7", likeCount.getText());
    }
}
