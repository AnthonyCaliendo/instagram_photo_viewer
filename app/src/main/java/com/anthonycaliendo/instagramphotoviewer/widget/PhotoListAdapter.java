package com.anthonycaliendo.instagramphotoviewer.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;

import java.util.List;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

/**
 * Extended {@link ArrayAdapter} which handles {@link Photo} objects.
 */
public class PhotoListAdapter extends ArrayAdapter<Photo> implements ListAdapter {

    public PhotoListAdapter(Context context, final List<Photo> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        PhotoItemView itemView = (PhotoItemView) view;
        if (itemView == null) {
            itemView = PhotoItemView.inflate(getContext(), parent);
            debug(this, "method=getView code=inflated_new_view");
        }

        itemView.setPhoto(getItem(position));

        return itemView;
    }
}
