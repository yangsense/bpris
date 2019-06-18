package com.ai.aris.server.cornerstone.service.interfaces;

import com.ai.aris.server.cornerstone.bean.ImageListResponse;
import com.ai.aris.server.cornerstone.model.StudySearchModel;
import com.ai.common.domain.ResultDTO;

/**
 * Created by lenovo on 2016/11/23.
 */
public interface IStudySV {

    public ResultDTO getStudyList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception ;
    public ResultDTO getSeriesList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception ;
    public ResultDTO getImageList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception ;
    public ImageListResponse getAllCheckList(StudySearchModel studySearchModel, ResultDTO resultDTO) throws Exception ;
}
