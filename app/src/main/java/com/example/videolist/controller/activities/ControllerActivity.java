package com.example.videolist.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.videolist.R;
import com.example.videolist.model.interfaces.LoadingHelper;
import com.example.videolist.model.VideoListDataConverter;
import com.example.videolist.networking.ConnectivityState;
import com.example.videolist.networking.RestApi;
import com.example.videolist.networking.model.DataListResponse;
import com.example.videolist.networking.model.Error;
import com.example.videolist.views.VideoListView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * The main controller activity. Data loading from server is handled here
 */
public class ControllerActivity extends AppCompatActivity implements LoadingHelper {

    private VideoListView videoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoListView = new VideoListView(this, this);
        setContentView(videoListView);

        loadData();
    }

    /**
     * Check for internet connection and make call to server. In case of internet connection problems
     * show error.
     */
    private void loadData() {
        if (ConnectivityState.isConnected(this)) {
            videoListView.showProgress(getString(R.string.loading_message));
            getVideoList();
        } else {
            videoListView.showError(getString(R.string.network_error));
        }
    }

    /**
     * Download data list and set to adapter or show error to user on fail
     */
    private void getVideoList() {
        Call<DataListResponse> videoListCall = RestApi.getWebService().getVideoList();
        videoListCall.enqueue(new RestApi.RestCallback<DataListResponse>() {
            @Override
            public void failure(Error error) {
                videoListView.hideProgress();
                videoListView.showError(error.message);
            }

            @Override
            public void success(Response<DataListResponse> response) {
                videoListView.hideProgress();
                videoListView.setData(VideoListDataConverter.convertToUIModel(response.body().categories));
            }
        });
    }

    @Override
    public void onRetry() {
        loadData();
    }
}
