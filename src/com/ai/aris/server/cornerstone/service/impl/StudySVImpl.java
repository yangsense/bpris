package com.ai.aris.server.cornerstone.service.impl;

import com.ai.aris.server.cornerstone.bean.*;
import com.ai.aris.server.cornerstone.model.StudySearchModel;
import com.ai.aris.server.cornerstone.service.interfaces.IStudySV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/23.
 */
public class StudySVImpl implements IStudySV{

    @Override
    public ResultDTO getStudyList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception{
        QryStudyBean[] beans  = null ;
        StringBuffer condition = new StringBuffer() ;
        Map<String,String> map = new HashMap();
        condition.append(" 1=1 ");
        if (StringUtils.isNotEmpty(studySearchModel.getPatientId())){
            condition.append(" and PATIENT_ID = :PATIENT_ID" );
            map.put("PATIENT_ID",studySearchModel.getPatientId());
        }
        if (StringUtils.isNotEmpty(studySearchModel.getPatientName())){
            condition.append(" and PATIENT_NAME = :PATIENT_NAME" );
            map.put("PATIENT_NAME",studySearchModel.getPatientName());
        }
         if (studySearchModel.getStudyKey()>0){
            condition.append(" and STUDY_KEY = :STUDY_KEY" );
            map.put("STUDY_KEY",String.valueOf(studySearchModel.getStudyKey()));
        }
         if (StringUtils.isNotEmpty(studySearchModel.getModality())){
            condition.append(" and MODALITY = :MODALITY" );
            map.put("MODALITY",studySearchModel.getModality());
        }
         if (StringUtils.isNotEmpty(studySearchModel.getStudyDate())){
            condition.append(" and STUDY_DATE = :STUDY_DATE" );
            map.put("STUDY_DATE",studySearchModel.getStudyDate());
        }

        int count = QryStudyEngine.getBeansCount(condition.toString(),map);
        if (count==0) return null ;
        beans =  QryStudyEngine.getBeans(condition.toString(),map);
        resultDTO.setRows(BeanUtils.beanToList(beans,StudyResponse.class));
        resultDTO.setRecords(beans.length);
        return  resultDTO ;
    }

    @Override
    public ImageListResponse getAllCheckList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception {
        QryStudyBean[] studyBeens = null;
        QrySeriesBean[] seriesBeens = null;
        QryImageBean[] imageBeens = null;
        ImageListResponse imageList = new ImageListResponse();
        java.util.List<SeriesResponse> seriesResponses = new ArrayList<SeriesResponse>();
        java.util.List<ImageResponse> imageResponses = new ArrayList<ImageResponse>();
        StringBuffer condition = new StringBuffer();
        Map<String, String> map = new HashMap();
        if(studySearchModel.getStudyKey()==0) return  null ;
            condition.append(" STUDY_KEY = :STUDY_KEY ");
            map.put("STUDY_KEY", String.valueOf(studySearchModel.getStudyKey()));

        studyBeens = QryStudyEngine.getBeans(condition.toString(), map);
        if (studyBeens == null) return null;
        imageList = BeanUtils.beanToList(studyBeens, ImageListResponse.class).get(0);

            //查询series
            condition = new StringBuffer();
            condition.append(" STUDY_KEY = :STUDY_KEY ");
            map.put("STUDY_KEY", String.valueOf(imageList.getStudyKey()));
            seriesBeens = QrySeriesEngine.getBeans(condition.toString(), map);
            if (seriesBeens == null)  return null;
            seriesResponses = BeanUtils.beanToList(seriesBeens, SeriesResponse.class);
            for (SeriesResponse series : seriesResponses) {
                condition = new StringBuffer();

                //查询image
                if(series.getSeriesKey()==0) break;
                condition.append(" SERIES_KEY = :SERIES_KEY ");
                map.put("SERIES_KEY", String.valueOf(series.getSeriesKey()));
                imageBeens = QryImageEngine.getBeans(condition.toString(), map);
                if (imageBeens == null) break;
                imageResponses = BeanUtils.beanToList(imageBeens, ImageResponse.class);
                series.setImageList(imageResponses);
            }
            imageList.setSeriesList(seriesResponses);

        return imageList;
    }


    @Override
    public ResultDTO getSeriesList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception {
        SeriesBean[] beans  = null ;
        StringBuffer condition = new StringBuffer() ;
        Map<String,String> map = new HashMap();
        int count = SeriesEngine.getBeansCount(condition.toString(),map);
        if (count>0){
            beans =  SeriesEngine.getBeans(condition.toString(),map);

        }
        resultDTO.setRows(BeanUtils.beanToList(beans,SeriesResponse.class));
        resultDTO.setRecords(beans.length);
        return  resultDTO ;
    }

    @Override
    public ResultDTO getImageList(StudySearchModel studySearchModel,ResultDTO resultDTO) throws Exception {
        ImageBean[] beans  = null ;
        StringBuffer condition = new StringBuffer() ;
        Map<String,String> map = new HashMap();
        int count = ImageEngine.getBeansCount(condition.toString(),map);
        if (count>0){
            beans =  ImageEngine.getBeans(condition.toString(),map);

        }
        resultDTO.setRows(BeanUtils.beanToList(beans,ImageResponse.class));
        resultDTO.setRecords(beans.length);
        return  resultDTO ;
    }
}
