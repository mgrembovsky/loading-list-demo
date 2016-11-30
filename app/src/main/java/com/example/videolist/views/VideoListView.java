package com.example.videolist.views;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.videolist.R;
import com.example.videolist.controller.adapters.DataListAdapter;
import com.example.videolist.model.ListItem;
import com.example.videolist.model.interfaces.LoadingHelper;

import java.util.List;


public class VideoListView extends BaseView {

    private LoadingHelper loadingHelper;
    private DataListAdapter adapter;

    public VideoListView(Context context, LoadingHelper loadingHelper) {
        super(context);
        this.loadingHelper = loadingHelper;
        init();
    }

    public VideoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VideoListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void onProgressCanceled() {
        showError(getContext().getString(R.string.loading_cancel_message));
    }

    /**
     * Method initiates UI components
     */
    private void init() {
        inflate(getContext(), R.layout.view_video_list, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_video_list);
        recyclerView.setAdapter(adapter = new DataListAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * Method shows error to user and adds ability to retry request
     * @param error String error message
     */
    public void showError(String error) {
        final Snackbar snackbar = Snackbar
                .make(this, error, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadingHelper.onRetry();
                    }
                });

        snackbar.show();
    }

    /**
     * Delegate method for set data into adapter
     * @param listItems list of objects to render
     */
    public void setData(List<ListItem> listItems) {
        adapter.setData(listItems);
    }
}
