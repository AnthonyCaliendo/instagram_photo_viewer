package com.anthonycaliendo.instagramphotoviewer.widget;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anthonycaliendo.instagramphotoviewer.R;
import com.anthonycaliendo.instagramphotoviewer.model.Comment;
import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

/**
 * View Holder for Photo items.
 */
public class PhotoItemView extends RelativeLayout {

    /**
     * The maximum number of comments to render.
     */
    private static final int MAX_COMMENTS = 2;

    /**
     * The {@link TextView} for the poster's username.
     */
    private final TextView posterUsername;

    /**
     * The {@link TextView} for the poster's full name.
     */
    private final TextView posterFullName;

    /**
     * The {@link TextView} for when the photo was posted at.
     */
    private final TextView postedAt;

    /**
     * The {@link TextView} for the caption.
     */
    private final TextView caption;

    /**
     * The {@link TextView} for the like count.
     */
    private final TextView likeCount;


    /**
     * The {@link TextView} for the comment count.
     */
    private final TextView commentCount;


    /**
     * The {@link ImageView} for this photo.
     */
    private final ImageView image;

    /**
     * The {@link ImageView} for the poster's profile image.
     */
    private final ImageView posterProfileImage;

    /**
     * The section to append comments into.
     */
    private final LinearLayout commentsSection;

    public PhotoItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.photo_item_view_children, this, true);

        this.posterUsername     = (TextView) findViewById(R.id.item_photo_poster_username);
        this.posterFullName     = (TextView) findViewById(R.id.item_photo_poster_full_name);
        this.caption            = (TextView) findViewById(R.id.item_photo_caption);
        this.image              = (ImageView) findViewById(R.id.item_photo_image);
        this.posterProfileImage = (ImageView) findViewById(R.id.item_photo_poster_profile_image);
        this.postedAt           = (TextView) findViewById(R.id.item_photo_posted_at);
        this.likeCount          = (TextView) findViewById(R.id.item_photo_like_count);
        this.commentCount       = (TextView) findViewById(R.id.item_photo_comment_count);
        this.commentsSection    = (LinearLayout) findViewById(R.id.item_photo_comments);
    }

    public static PhotoItemView inflate(final Context context, final ViewGroup parent) {
        return (PhotoItemView)LayoutInflater.from(context).inflate(R.layout.photo_item_view, parent, false);
    }

    /**
     * Sets the photo onto the view, setting all appropriate fields.
     * @param photo
     *      the photo to set onto this view
     */
    public void setPhoto(Photo photo) {
        posterUsername.setText(photo.getPosterUsername());

        posterFullName.setText(photo.getPosterFullName());
        if (posterFullName.getText() == null || posterFullName.getText() == "") {
            posterFullName.setVisibility(INVISIBLE);
        } else {
            posterFullName.setVisibility(VISIBLE);
        }

        caption.setText(photo.getCaption());

        postedAt.setText(DateUtils.getRelativeTimeSpanString(photo.getPostedAt().getTimeInMillis(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));

        likeCount.setText(NumberFormat.getNumberInstance().format(photo.getLikeCount()));

        commentCount.setText(NumberFormat.getNumberInstance().format(photo.getCommentCount()));

        Picasso.with(getContext())
                .load(photo.getPosterProfileImageUrl())
                .placeholder(R.drawable.default_profile_image)
                .error(R.drawable.default_profile_image)
                .resize(0, 150)
                .transform(new CircleTransformation())
                .into(posterProfileImage);

        Picasso.with(getContext())
                .load(photo.getImageUrl())
                .placeholder(R.drawable.default_photo_image)
                .error(R.drawable.default_photo_image)
                .into(image);

        appendComments(photo.getComments());
    }

    /**
     * Appends the top comments to the view.
     * @param comments
     *      the comments to append
     */
    private void appendComments(final List<Comment> comments) {
        commentsSection.removeAllViews();

        for (int commentIndex = 0; commentIndex < comments.size(); commentIndex++) {
            if ((commentIndex + 1) > MAX_COMMENTS) {
                break;
            }

            final Comment comment        = comments.get(commentIndex);
            final View commentView       = commentsSection.inflate(getContext(), R.layout.comment_item_view, null);
            final TextView commentText   = (TextView) commentView.findViewById(R.id.comment_item_text);
            final TextView usernameText  = (TextView) commentView.findViewById(R.id.comment_item_commenter_username);
            final ImageView profileImage = (ImageView) commentView.findViewById(R.id.comment_item_profile_image);

            commentText.setText(comment.getText());
            usernameText.setText(comment.getCommenterUsername());

            Picasso.with(getContext())
                    .load(comment.getCommenterProfileImageUrl())
                    .placeholder(R.drawable.default_small_profile_image)
                    .error(R.drawable.default_small_profile_image)
                    .transform(new CircleTransformation())
                    .resize(0, 75)
                    .into(profileImage);

            commentsSection.addView(commentView);
        }
    }

}
