package com.anthonycaliendo.instagramphotoviewer;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.anthonycaliendo.instagramphotoviewer.model.Photo;
import com.anthonycaliendo.instagramphotoviewer.model.dao.InstagramClient;
import com.anthonycaliendo.instagramphotoviewer.util.Configuration;
import com.anthonycaliendo.instagramphotoviewer.widget.PhotoListAdapter;
import com.loopj.android.http.AsyncHttpClient;

import java.util.List;

import static com.anthonycaliendo.instagramphotoviewer.util.Instrumentation.debug;

public class ViewPopularStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_popular_stream);

        final ListView todosListView            = (ListView) findViewById(R.id.view_popular_stream_photos);
        final String instagramClientId          = Configuration.getInstagramClientId();
        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.view_popular_stream_photos_swipe_refresh);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                debug(this, "handler=onRefresh code=refreshing_popular_stream");
                fetchItemsAndRefreshView(todosListView, instagramClientId);
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        fetchItemsAndRefreshView(todosListView, instagramClientId);
    }

    private void fetchItemsAndRefreshView(final ListView todosListView, final String instagramClientId) {
        new InstagramClient(new AsyncHttpClient()).fetchPopularPhotos(new InstagramClient.ResponseHandler() {
            @Override
            public void onSuccess(List<Photo> photos) {
                final PhotoListAdapter todoListAdapter = new PhotoListAdapter(ViewPopularStreamActivity.this, photos);
                todosListView.setAdapter(todoListAdapter);
            }
        });
    }
}
