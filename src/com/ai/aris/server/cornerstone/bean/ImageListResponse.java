package com.ai.aris.server.cornerstone.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/11/25.
 */
public class ImageListResponse extends BaseResponse{
    //patient
    private String patientName ;
    private String patientId ;

    //study
    private String studyDate ;
    private String modality ;
    private String studyDescription ;
    private long numImages ;
    private String studyId ;
    private long studyKey ;

    public long getStudyKey() {
        return studyKey;
    }

    public void setStudyKey(long studyKey) {
        this.studyKey = studyKey;
    }

    private List<SeriesResponse> seriesList;

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public long getNumImages() {
        return numImages;
    }

    public void setNumImages(long numImages) {
        this.numImages = numImages;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<SeriesResponse> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SeriesResponse> seriesList) {
        this.seriesList = seriesList;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }


}
