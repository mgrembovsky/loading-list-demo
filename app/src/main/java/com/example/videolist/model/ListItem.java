package com.example.videolist.model;

import android.os.Parcel;

/**
*
* */
public class ListItem {
    private String title;
    private String studio;
    private String thumbnail;

    protected ListItem(Parcel in) {
        title = in.readString();
        studio = in.readString();
        thumbnail = in.readString();
    }

    public ListItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
