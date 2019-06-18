package com.ai.aris.web.cornerstone;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.cornerstone.bean.ImageListResponse;
import com.ai.aris.server.cornerstone.bean.StudyListResponse;
import com.ai.aris.server.cornerstone.bean.StudyRequest;
import com.ai.aris.server.cornerstone.bean.StudyResponse;
import com.ai.aris.server.cornerstone.model.StudySearchModel;
import com.ai.aris.server.cornerstone.service.interfaces.IStudySV;
import com.ai.common.domain.ResultDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/23.
 */
@Controller
@RequestMapping("/study")
public class CornerstoneService {
    @Autowired
    @Qualifier("prototypeObjMapper")
    private ObjectMapper objectMapper;
    private IStudySV studySV = (IStudySV) ServiceFactory.getService(IStudySV.class);

    @RequestMapping("/getStudyList")
    @ResponseBody
    public String getStudyList(StudyRequest studyRequest, HttpServletResponse response) throws JsonProcessingException {

//        0000表示查询成功
//        0001表示查询失败
//        0002 无记录
//        0003 其它异常
        //  response.setHeader("Access-Control-Allow-Headers","Content-Type, api_key, Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        StudySearchModel searchModel = new StudySearchModel();
        if (StringUtils.isNotEmpty(studyRequest.getPatientId())) {
            searchModel.setPatientId(studyRequest.getPatientId());
        }
        if (StringUtils.isNotEmpty(studyRequest.getPatientName())) {
            searchModel.setPatientName(studyRequest.getPatientName());
        }
        if (studyRequest.getStudyKey() > 0) {
            searchModel.setStudyKey(studyRequest.getStudyKey());
        }
        if (StringUtils.isNotEmpty(studyRequest.getModality())) {
            searchModel.setModality(studyRequest.getModality());
        }
        if (StringUtils.isNotEmpty(studyRequest.getStudyDate())) {
            searchModel.setStudyDate(studyRequest.getStudyDate());
        }

        ResultDTO resultDao = new ResultDTO(0, 100);
        java.util.List<StudyResponse> studyResponses = new ArrayList<StudyResponse>();
        StudyListResponse listResponse = new StudyListResponse();
        try {
            resultDao = studySV.getStudyList(searchModel, resultDao);
            if (resultDao == null) {
                listResponse.setCode("0002");
                listResponse.setMessage("查询记录不存在");
                return objectMapper.writeValueAsString(listResponse);
            }
            studyResponses = resultDao.getRows();
        } catch (Exception e) {
            e.printStackTrace();
            listResponse.setCode("0001");
            listResponse.setMessage("查询失败");
            return objectMapper.writeValueAsString(listResponse);
        }
        listResponse.setCode("0000");
        listResponse.setMessage("查询成功");
        listResponse.setStudyList(studyResponses);
        return objectMapper.writeValueAsString(listResponse);
    }

    @RequestMapping("/getImageList")
    @ResponseBody
    public String getImageList(StudyRequest studyRequest, HttpServletResponse response) throws JsonProcessingException {

//        0000 表示查询成功
//        0001 表示查询失败
//        0002 表示无记录
//        0003 表示参数有误
//        0004 表示其它异常
        response.setHeader("Access-Control-Allow-Origin", "*");
        StudySearchModel searchModel = new StudySearchModel();
        ResultDTO resultDao = new ResultDTO(0, 100);
        ImageListResponse imageList = new ImageListResponse();
        StudyListResponse listResponse = new StudyListResponse();
        //检验请求参数
        if (studyRequest.getStudyKey() == 0) {
            imageList.setCode("0003");
            imageList.setMessage("参数有误");
            return objectMapper.writeValueAsString(imageList);
        }
        searchModel.setStudyKey(studyRequest.getStudyKey());

        try {
            imageList = studySV.getAllCheckList(searchModel, resultDao);
            if (imageList == null) {
                imageList.setCode("0002");
                imageList.setMessage("查询记录不存在");
                return objectMapper.writeValueAsString(imageList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageList.setCode("0004");
            imageList.setMessage("查询异常");
            return objectMapper.writeValueAsString(imageList);
        }
        imageList.setCode("0000");
        imageList.setMessage("查询成功");
        return objectMapper.writeValueAsString(imageList);
    }

}
