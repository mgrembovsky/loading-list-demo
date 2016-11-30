package com.example.videolist.networking.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataListResponse extends BaseResponse {

    public List<Category> categories;

    public class Category {
        public String name;
        public List<ListObject> videos;

        public class ListObject {

            public String title;
            public String subtitle;
            public String studio;

            @SerializedName("image-480x270")
            public String image480x270;

            @SerializedName("image-780x1200")
            public String image780x200;

            @SerializedName("thumb")
            public String thumbnail;
        }
    }
}
