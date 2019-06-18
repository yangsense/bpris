package com.ai.aris.server.cornerstone.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/11/23.
 */
public class StudyListResponse extends BaseResponse {
    private List<StudyResponse> studyList;

    public List<StudyResponse> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<StudyResponse> studyList) {
        this.studyList = studyList;
    }
}
