package com.example.videolist.model;

import com.example.videolist.networking.RestApi;
import com.example.videolist.networking.model.DataListResponse;

import java.util.ArrayList;
import java.util.List;

public class VideoListDataConverter {

    /**
     * Method that converts downloaded POJO model to simple UI object
     *
     * @param categories List of categories
     * @return List of simple UI objects
     */
    public static List<ListItem> convertToUIModel(List<DataListResponse.Category> categories) {
        List<ListItem> result = new ArrayList<>();
        if (categories == null || categories.isEmpty()) {
            return result;
        }

        for (DataListResponse.Category category : categories) {
            if (category.videos != null && !category.videos.isEmpty()) {
                for (DataListResponse.Category.ListObject listObject : category.videos) {
                    ListItem item = new ListItem();
                    item.setTitle(listObject.title);
                    item.setStudio(listObject.studio);
                    item.setThumbnail(getFullURL(listObject.thumbnail));
                    result.add(item);
                }
                return result;
            }
        }

        return result;
    }

    private static String getFullURL(String resourceUrl) {
        return RestApi.ENDPOINT + resourceUrl;
    }
}
