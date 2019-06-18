package com.ai.aris.server.cornerstone.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/11/23.
 */
public class SeriesResponse {

    private String seriesUid ;
    private String seriesDescription ;
    private long seriesKey ;
    private String seriesNumber ;




    private java.util.List<ImageResponse> imageList;

    public String getSeriesUid() {
        return seriesUid;
    }

    public void setSeriesUid(String seriesUid) {
        this.seriesUid = seriesUid;
    }

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public long getSeriesKey() {
        return seriesKey;
    }

    public void setSeriesKey(long seriesKey) {
        this.seriesKey = seriesKey;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public List<ImageResponse> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageResponse> imageList) {
        this.imageList = imageList;
    }
}
