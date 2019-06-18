package com.ai.aris.server.workstation.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import net.sf.json.JSONObject;
import oracle.sql.CLOB;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AisServiceLogBean;
import com.ai.aris.server.basecode.bean.AisServiceLogEngine;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscRuleBean;
import com.ai.aris.server.basecode.bean.AiscRuleEngine;
import com.ai.aris.server.basecode.bean.AiscUserPhraseBean;
import com.ai.aris.server.basecode.bean.AiscUserPhraseEngine;
import com.ai.aris.server.common.bean.AisHzBackurlBean;
import com.ai.aris.server.common.bean.AisHzBackurlEngine;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.Base64Encoder;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.interfacereal.AIbean.QryImageForAIBean;
import com.ai.aris.server.interfacereal.AIbean.QryImageForAIEngine;
import com.ai.aris.server.interfacereal.model.AisAiDiagnosisModel;
import com.ai.aris.server.webservice.bean.AisStudyReportData;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.bean.aitencent.AIQryResultResponse;
import com.ai.aris.server.webservice.bean.aitencent.DicomResultRequest;
import com.ai.aris.server.webservice.bean.aitencent.JsonRootBean;
import com.ai.aris.server.webservice.bean.aitencent.MarkList;
import com.ai.aris.server.webservice.bean.aitencent.QryResultRequest;
import com.ai.aris.server.webservice.bean.aitencent.QryResultResponse;
import com.ai.aris.server.webservice.bean.aitencent.UpdateAiResultRequest;
import com.ai.aris.server.webservice.bean.aitencent.UploadDicomRequest;
import com.ai.aris.server.webservice.bean.aitencent.UploadDicomRequest.ImagesBean;
import com.ai.aris.server.webservice.bean.aitencent.UploadDicomResponse;
import com.ai.aris.server.webservice.bean.realtime.HzStudyBackResponse;
import com.ai.aris.server.webservice.imageservice.FileServiceStub;
import com.ai.aris.server.webservice.imageservice.FileServiceStub.UpFileResult;
import com.ai.aris.server.workstation.bean.AisFilesInfoBean;
import com.ai.aris.server.workstation.bean.AisFilesInfoEngine;
import com.ai.aris.server.workstation.bean.AisKnowledgebaseBean;
import com.ai.aris.server.workstation.bean.AisKnowledgebaseEngine;
import com.ai.aris.server.workstation.bean.AisModalityworklistBean;
import com.ai.aris.server.workstation.bean.AisModalityworklistEngine;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoEngine;
import com.ai.aris.server.workstation.bean.AisReportFilesBean;
import com.ai.aris.server.workstation.bean.AisReportFilesEngine;
import com.ai.aris.server.workstation.bean.AisReportUploadBean;
import com.ai.aris.server.workstation.bean.AisReportUploadEngine;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyMessageBean;
import com.ai.aris.server.workstation.bean.AisStudyMessageEngine;
import com.ai.aris.server.workstation.bean.AisStudyReportBean;
import com.ai.aris.server.workstation.bean.AisStudyReportEngine;
import com.ai.aris.server.workstation.bean.AisStudyReportHBean;
import com.ai.aris.server.workstation.bean.AisStudyReportHEngine;
import com.ai.aris.server.workstation.bean.AiscTemplatecatDirBean;
import com.ai.aris.server.workstation.bean.AiscTemplatecatDirEngine;
import com.ai.aris.server.workstation.bean.QryRegInfoBean;
import com.ai.aris.server.workstation.bean.QryRegInfoEngine;
import com.ai.aris.server.workstation.bean.QryReportBrowseBean;
import com.ai.aris.server.workstation.bean.QryReportBrowseEngine;
import com.ai.aris.server.workstation.bean.QryReportBrowseListBean;
import com.ai.aris.server.workstation.bean.QryReportBrowseListEngine;
import com.ai.aris.server.workstation.bean.QryReportFileBean;
import com.ai.aris.server.workstation.bean.QryReportFileEngine;
import com.ai.aris.server.workstation.bean.QryReportHBean;
import com.ai.aris.server.workstation.bean.QryReportHEngine;
import com.ai.aris.server.workstation.bean.QryServerMediumBean;
import com.ai.aris.server.workstation.bean.QryServerMediumEngine;
import com.ai.aris.server.workstation.bean.QryTemplateTreeBean;
import com.ai.aris.server.workstation.bean.QryTemplateTreeEngine;
import com.ai.aris.server.workstation.model.AisKnowledgebaseModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.TemplateContent;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.ai.aris.web.controller.workstation.StudyReportController;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.json.CustomObjectMapper;
import com.ai.common.util.ApplicationUtil;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.Constants;
import com.ai.common.util.DateUtils;
import com.ai.common.util.FTPSUtil;
import com.ai.common.util.FileUtil;
import com.ai.common.util.JsonUtil;
import com.ai.common.util.PropertiesUtils;
import com.ai.common.util.ServiceUtil;


public class StudyReportSVImpl implements IStudyReportSV {
    private static Log logger = LogFactory.getLog(StudyReportController.class);
    FTPSUtil ftpUtil = null;
    private static IPatientRegSV sv = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);

    public Map getSeq(String orgId, String locId) throws Exception {
        Map map = new HashMap();

        //1. 查病人全局序列
        long patientGlobalid = ServiceUtil.getSequence("SEQGOBALPATIENTID");
        String patientId = getIdByRule("PATIENT_ID", orgId, locId);
        String studyAccnumber = getIdByRule("STUDY_ACCNUMBER", orgId, locId);
        map.put("patientGlobalid", patientGlobalid);
        map.put("patientId", patientId);
        map.put("studyAccnumber", studyAccnumber);

        return map;
    }

    public String getIdByRule(String fieldName, String orgId, String locId) throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String ids = "";
        AiscRuleBean pRuleBean = getRule(orgId, locId, fieldName);
        if (pRuleBean != null) {
            //1.前缀
            String prix = pRuleBean.getRulePrix();
            //2.长度
            long len = pRuleBean.getRuleLen();
            //3.开始序号
            long startIndex = pRuleBean.getRuleStartindex();
            if (pRuleBean.getRuleType() == 1) {
                //按日期增量规则生成
                String eLen = prix + String.valueOf(startIndex);
                long cLen = len - eLen.length();
                String blen = "";
                for (int i = 0; i < cLen; i++) {
                    blen += "0";
                }
                ids = prix + blen + startIndex;
            } else if (pRuleBean.getRuleType() == 2) {
                //按增量规则生成
                String eLen = prix + String.valueOf(dateFormat.format(date)) + String.valueOf(startIndex);
                long cLen = len - eLen.length();
                String blen = "";
                for (int i = 0; i < cLen; i++) {
                    blen += "0";
                }
                ids = prix + String.valueOf(dateFormat.format(date)) + blen + startIndex;
            }
            //规则递增更新
            startIndex += 1;
            AiscRuleBean newBean = new AiscRuleBean();
            newBean.setRuleId(pRuleBean.getRuleId());
            pRuleBean.setRuleStartindex(startIndex);
            newBean.setStsToOld();
            DataContainerFactory.copyNoClearData(pRuleBean, newBean);
            AiscRuleEngine.save(newBean);
        }

        //如果没有配置，则取序列
        if ("".equals(ids)) {
            if ("PATIENT_ID".equals(fieldName)) {
                ids = String.valueOf(ServiceUtil.getSequence("SEQGOBALPATIENTID"));
            } else if ("STUDY_ACCNUMBER".equals(fieldName)) {
                ids = String.valueOf(ServiceUtil.getSequence("SEQSTUDYACCNUMBER"));
            }
        }
        return ids;
    }

    //取登记号
    public String getPatientId(String dateStr) throws Exception {
        String sql = " SELECT MAX(PATIENT_ID) FROM AIS_PATIENTINFO WHERE SUBSTR(PATIENT_ID, 0, 8) = " + dateStr + " ";
        AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeansFromSql(sql, null);

        if (beans[0].getPatientId() != null && !"".equals(beans[0].getPatientId())) {
            return String.valueOf(Integer.valueOf(beans[0].getPatientId()) + 1);
        } else {
            return dateStr + 001;
        }
    }

    //取检查号
    public String getStudyAccnumber(String dateStr) throws Exception {
        String sql = " SELECT MAX(STUDY_ACCNUMBER) FROM AIS_STUDYINFO WHERE SUBSTR(STUDY_ACCNUMBER, 0, 8) = " + dateStr + " ";
        AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeansFromSql(sql, null);

        if (beans[0].getPatientId() != null && !"".equals(beans[0].getPatientId())) {
            return String.valueOf(Integer.valueOf(beans[0].getPatientId()) + 1);
        } else {
            return dateStr + "001";
        }
    }


    /*  查询序列生成规则
     *  先按机构+科室+字段检索，检索不到再按机构+字段检索，检查不到就直接按字段检索
     */
    public AiscRuleBean getRule(String orgId, String locId, String ruleField) throws Exception {
        StringBuffer condition1 = new StringBuffer();
        StringBuffer condition2 = new StringBuffer();
        StringBuffer condition3 = new StringBuffer();
        condition1.append(" 1=1");
        if (isNotBlank(orgId) && !"-1".equals(orgId)) {
            condition1.append(" AND ORG_ID = '" + orgId + "'");
        }
        if (isNotBlank(locId) && !"-1".equals(locId)) {
            condition2.append(" AND LOC_ID = '" + locId + "'");
        }
        if (isNotBlank(ruleField) && !"-1".equals(ruleField)) {
            condition3.append(" AND RULE_FIELD = '" + ruleField + "'");
        } else {
            return null;
        }
        AiscRuleBean[] ruleBean = AiscRuleEngine.getBeans((String.valueOf(condition1) + String.valueOf(condition2) + String.valueOf(condition3)), null);

        if (ruleBean.length > 0) {
            return ruleBean[0];
        } else {
            ruleBean = AiscRuleEngine.getBeans((String.valueOf(condition1) + String.valueOf(condition3)), null);
            if (ruleBean.length > 0) {
                return ruleBean[0];
            } else {
                ruleBean = AiscRuleEngine.getBeans((String.valueOf(condition3)), null);
                if (ruleBean.length > 0) {
                    return ruleBean[0];
                } else {
                    return new AiscRuleBean();
                }
            }
        }
    }

    //保存病人登记记录
    public long savePatientReg(User user, AisPatientInfoBean patientBean,
                               AisStudyInfoBean studyBean, String studyitemDesc,
                               String studyitemBodyinfo, String studyitemNumber,
                               String studyitemPrice) throws Exception {
        Timestamp dateTime = new Timestamp(new Date().getTime());

        //病人信息
        AisPatientInfoEngine.save(patientBean);
        //检查信息
        long studyInfoId = ServiceUtil.getSequence("SEQSTUDYINFOID");
        studyBean.setStudyinfoId(studyInfoId);
        studyBean.setStudyAppdate(dateTime);//申请时间
        studyBean.setStudyDatetime(dateTime);//登记时间
        studyBean.setStudystatusCode("APP");
        studyBean.setStudyOperationid(user.getOperatorId());//操作员
        AisStudyInfoEngine.save(studyBean);
        //检查项目
        if (studyitemDesc != null && !"-1".equals(studyitemDesc)) {
            String[] itemDescArray = studyitemDesc.split(",");
            String[] bodyInfoArray = studyitemBodyinfo.split(",");
            String[] itemNumberArray = studyitemNumber.split(",");
            String[] itemPriceArray = studyitemPrice.split(",");
            for (int i = 0; i < itemDescArray.length; i++) {
                AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
                bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
                bean.setStudyinfoId(studyBean.getStudyinfoId());
                String[] itemArray = itemDescArray[i].split("_");
                bean.setStudyitemCode(itemArray[0]);
                bean.setStudyitemDesc(itemArray[1]);
                if (studyitemBodyinfo != null && !"-1".equals(studyitemBodyinfo)) {
                    String[] bodyArray = bodyInfoArray[i].split("_");
                    bean.setStudyitemBodyinfo(bodyArray[1]);
                }
                if (studyitemNumber != null && !"".equals(studyitemNumber)) {
                    bean.setStudyitemNumber(Integer.valueOf(itemNumberArray[i]));
                }
                if (studyitemPrice != null && !"".equals(studyitemPrice)) {
                    bean.setStudyitemPrice(Float.valueOf(itemPriceArray[i]));
                }
                bean.setStudyitemStatus("N");
                AisStudyItemInfoEngine.save(bean);
            }
        }
        return studyInfoId;
    }

    //登记信息查询
    public QryRegInfoBean getRegInfo(String studyinfoId) throws Exception {

        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        QryRegInfoBean[] regInfoBeans = QryRegInfoEngine.getBeans(condition.toString(), null);
        if (regInfoBeans.length > 0) {
            for (QryRegInfoBean regInfoBean : regInfoBeans) {
                regInfoBean.setStudyDoctorid(getCareProvInfo(regInfoBean.getStudyDoctorid()));
                regInfoBean.setAidDoctorid(getCareProvInfo(regInfoBean.getAidDoctorid()));
            }
            return regInfoBeans[0];
        } else {
            return new QryRegInfoBean();
        }
    }

    //根据医生编码查医姓名
    public String getCareProvInfo(String careProvCode) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(careProvCode)) {
            condition.append(" and careprov_id = '" + careProvCode + "'");
        } else {
            return "";
        }
        AiscCareProvBean[] beans = AiscCareProvEngine.getBeans(condition.toString(), null);
        if (beans.length > 0) {
            return beans[0].getCareprovName();
        } else {
            return "";
        }

    }

    //模板记录初始化
    public QryTemplateTreeBean[] getTemplateTree(String id, String orgId, String locId,
                                                 String operatorId, String modalityId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" TEMPLATEDIR_ID IN "
                + " (SELECT DISTINCT TEMPLATEDIR_ID "
                + "  FROM AISC_TemplateDIR "
                + "  WHERE 1 = 1 "
                + "  START WITH TEMPLATEDIR_ID IN "
                + "             (SELECT T.TEMPLATEDIR_ID "
                + "               FROM AISC_TPLDIR2LOC T "
                + "              WHERE ORG_ID = " + orgId + " "
                + "                 AND LOC_ID = " + locId + " ) "
                + " CONNECT BY PRIOR TEMPLATEDIR_ID = TEMPLATEDIR_PDIRID) ");
        if (isNotBlank(id)) {
            condition.append(" AND TEMPLATEDIR_PDIRID = " + id);
        }
        if (isNotBlank(operatorId)) {
            condition.append(" AND OPERATOR_ID = '" + operatorId + "'");
        }
        if (isNotBlank(modalityId)&&!"0".equals(modalityId)) {
            condition.append(" AND MODALITY_ID = '" + modalityId + "'");
        }
        QryTemplateTreeBean[] ruleBean = QryTemplateTreeEngine.getBeans(condition.toString(), null);

        return ruleBean;
    }

    //模板详情查询
    public Map getTempInfo(String templatedirId) throws Exception {
        Map map = new HashMap();
        Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = " SELECT * FROM AISC_TEMPLATECONTENT WHERE TEMPLATEDIR_ID='" + templatedirId + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            String templateExam = "";
            String templateResult = "";
            String contentId = "";
            String ispositive = "";
            if (rs.next()) {
                contentId = rs.getString("TEMPLATECONTENT_ID");
                ispositive = rs.getString("ISPOSITIVE");
                Clob exam = rs.getClob("TEMPLATE_EXAM");//检查所见
                Clob result = rs.getClob("TEMPLATE_RESULT");//诊断结果
                if (exam != null) {
                    templateExam = exam.getSubString(1, (int) exam.length());
                    System.out.println("CLOB字段的值：" + templateExam);
                }
                if (result != null) {
                    templateResult = result.getSubString(1, (int) result.length());
                }
            }
            map.put("contentId", contentId);
            map.put("ispositive", ispositive);
            map.put("templateExam", templateExam.replace("<br type=\"_moz\" />", "").replace("&nbsp;", ""));
            map.put("templateResult", templateResult.replace("<br type=\"_moz\" />", "").replace("&nbsp;", ""));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return map;

    }

    //保存挂起报告
    public String saveUpReport(AisStudyReportBean reportBean, User user, String studyConsultorg, String studyConsultloc, String reportId,HttpServletRequest request,String imgArr,String orgId) throws Exception {

        Timestamp dateTime = new Timestamp(new Date().getTime());
        //1.修改状态为报告未完成
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(reportBean.getStudyinfoId());
        sbean.setStudystatusCode("UnCompleted");//报告未完成
        sbean.setStudyHavereport(1);
        if (!"-1".equals(studyConsultorg) && !"undefined".equals(studyConsultorg)) {
            sbean.setStudyConsultorg(studyConsultorg);
        }
        if (!"-1".equals(studyConsultloc) && !"undefined".equals(studyConsultloc)) {
            sbean.setStudyConsultloc(Long.parseLong(studyConsultloc));
        }
        if (sbean.getStudyStarttime() == null || "".equals(sbean.getStudyStarttime())) {
            sbean.setStudyStarttime(dateTime);
        }
        AisStudyInfoEngine.save(sbean);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //已保存，则更新。未保存，插入一条
        //if("".equals(String.valueOf(reportBean.getReportId())) && String.valueOf(reportBean.getReportId()) != null){
        if (!"".equals(reportId)) {
            AisStudyReportBean oldBean = AisStudyReportEngine.getBean(Long.parseLong(reportId));

            oldBean.setReportDatetime(dateTime);
            oldBean.setReportDoctorid(user.getCareProvId());
            oldBean.setReportRecord(user.getCareProvId());
            DataContainerFactory.copyNoClearData(reportBean, oldBean);
            AisStudyReportEngine.save(oldBean);
            
            //已录入历史报告
            AisStudyReportHBean[] hBean = getReportHisBeans(Long.parseLong(reportId), reportBean.getStudyinfoId(), "UnCompleted");
            if(hBean!=null&&hBean.length>0){
            	hBean[0].setStsToOld();
                hBean[0].setReportDatetime(dateTime);
                hBean[0].setReportDoctorid(user.getCareProvId());
                hBean[0].setReportRecord(user.getCareProvId());
                hBean[0].setContrastsRemark(user.getOperatorName() + "医生已挂起报告[" + sdf.format(dateTime) + "]");
                DataContainerFactory.copyNoClearData(reportBean, hBean[0]);
                AisStudyReportHEngine.save(hBean[0]);
            }else{
            	long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
                AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(oldBean.getReportId());
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportDatetime(dateTime);
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportDoctorid(user.getCareProvId());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportIsprinted(0);//未打印
                newBean.setStatus("UnCompleted");
                newBean.setReportNum(1);
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setContrastsRemark(user.getOperatorName() + "医生已挂起报告[" + dateTime + "]");
                AisStudyReportHEngine.save(newBean);
            }
            //删除设备工作列表记录
            deleteModWorkList(sbean.getStudyAccnumber());
          //上传临时报告
            String result = uploadReportTemp(reportBean,Long.parseLong(reportId), request, imgArr,orgId);
            if(!"success".equals(result)){
            	throw new Exception("上传报告失败："+result);
            }
            return reportId+"||"+result;
        } else {
            //写报告
            long newReportId = ServiceUtil.getSequence("SEQREPORTID");
            reportBean.setReportId(newReportId);
            reportBean.setReportDatetime(dateTime);
            reportBean.setReportDoctorid(user.getCareProvId());
            reportBean.setReportRecord(user.getCareProvId());
            reportBean.setReportIsprinted(0);//未打印
            AisStudyReportEngine.save(reportBean);
            //删除设备工作列表记录
            deleteModWorkList(sbean.getStudyAccnumber());
            //操作记录
            sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "UnCompleted");
            //记录到日志
            long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
            AisStudyReportHBean hBean = new AisStudyReportHBean();
            hBean.setReportHistoryId(historyId);
            hBean.setReportId(newReportId);
            hBean.setStudyinfoId(reportBean.getStudyinfoId());
            hBean.setReportExam(reportBean.getReportExam());
            hBean.setReportResult(reportBean.getReportResult());
            hBean.setReportDatetime(dateTime);
            hBean.setReportIspositive(reportBean.getReportIspositive());
            hBean.setReportDoctorid(user.getCareProvId());
            hBean.setReportRecord(user.getCareProvId());
            hBean.setReportIsprinted(0);//未打印
            hBean.setStatus("UnCompleted");
            hBean.setReportNum(1);
            hBean.setReportRemark(reportBean.getReportRemark());
            hBean.setContrastsRemark(user.getOperatorName() + "医生已挂起报告[" + dateTime + "]");
            AisStudyReportHEngine.save(hBean);
            //上传临时报告
            String result = uploadReportTemp(reportBean, newReportId, request, imgArr,orgId);
            if(!"success".equals(result)){
            	throw new Exception("上传报告失败："+result);
            }
            return newReportId+"||"+result;
        }
    }

    //保存报告
    public String saveReport(AisStudyReportBean reportBean, User user, String studyConsultorg, String studyConsultloc, String reportId,HttpServletRequest request,String imgArr,String orgId) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateTime = new Timestamp(new Date().getTime());
        //1.修改检查单状态为已录入
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(reportBean.getStudyinfoId());
        sbean.setStudystatusCode("HAVERPT");//已录入
        sbean.setStudyHavereport(1);
        if (!"-1".equals(studyConsultorg) && !"undefined".equals(studyConsultorg)) {
            sbean.setStudyConsultorg(studyConsultorg);
        }
        if (!"-1".equals(studyConsultloc) && !"undefined".equals(studyConsultloc)) {
            sbean.setStudyConsultloc(Long.parseLong(studyConsultloc));
        }
        if (sbean.getStudyStarttime() == null || "".equals(sbean.getStudyStarttime())) {
            sbean.setStudyStarttime(dateTime);
        }
        sbean.setStudyCreatetime(dateTime);
        AisStudyInfoEngine.save(sbean);

        //已保存，则更新。未保存，插入一条
        if (!"".equals(reportId)) {
            AisStudyReportBean oldBean = AisStudyReportEngine.getBean(Long.parseLong(reportId));

            oldBean.setReportDatetime(dateTime);
            oldBean.setReportDoctorid(user.getCareProvId());
            oldBean.setReportRecord(user.getCareProvId());
            DataContainerFactory.copyNoClearData(reportBean, oldBean);
            AisStudyReportEngine.save(oldBean);

            //已录入历史报告
            AisStudyReportHBean[] hBean = getReportHisBeans(Long.parseLong(reportId), reportBean.getStudyinfoId(), "HAVERPT");
            if(hBean!=null&&hBean.length>0){
            	hBean[0].setStsToOld();
                hBean[0].setReportDatetime(dateTime);
                hBean[0].setReportDoctorid(user.getCareProvId());
                hBean[0].setReportRecord(user.getCareProvId());
                hBean[0].setContrastsRemark(user.getOperatorName() + "医生已录入报告[" + sdf.format(dateTime) + "]");
                DataContainerFactory.copyNoClearData(reportBean, hBean[0]);
                AisStudyReportHEngine.save(hBean[0]);
            }else{
            	long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
            	AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(Long.parseLong(reportId));
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportDatetime(dateTime);
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportDoctorid(user.getCareProvId());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportIsprinted(0);//未打印
                newBean.setStatus("HAVERPT");
                newBean.setReportNum(1);
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setContrastsRemark(user.getOperatorName() + "医生已录入报告[" + dateTime + "]");
                AisStudyReportHEngine.save(newBean);
            }

            //删除设备工作列表记录
            deleteModWorkList(sbean.getStudyAccnumber());
            //上传临时报告
            String result = uploadReportTemp(reportBean,Long.parseLong(reportId), request, imgArr,orgId);
            if(!"success".equals(result)){
            	throw new Exception("上传报告失败："+result);
            }
            return reportId+"||"+result;
        } else {
            //写报告
            long newReportId = ServiceUtil.getSequence("SEQREPORTID");
            reportBean.setReportId(newReportId);
            reportBean.setReportDatetime(dateTime);
            reportBean.setReportDoctorid(user.getCareProvId());
            reportBean.setReportRecord(user.getCareProvId());
            reportBean.setReportIsprinted(0);//未打印
            AisStudyReportEngine.save(reportBean);

            long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
            AisStudyReportHBean hBean = new AisStudyReportHBean();
            hBean.setReportHistoryId(historyId);
            hBean.setReportId(newReportId);
            hBean.setStudyinfoId(reportBean.getStudyinfoId());
            hBean.setReportExam(reportBean.getReportExam());
            hBean.setReportResult(reportBean.getReportResult());
            hBean.setReportDatetime(dateTime);
            hBean.setReportIspositive(reportBean.getReportIspositive());
            hBean.setReportDoctorid(user.getCareProvId());
            hBean.setReportRecord(user.getCareProvId());
            hBean.setReportIsprinted(0);//未打印
            hBean.setStatus("HAVERPT");
            hBean.setReportNum(1);
            hBean.setReportRemark(reportBean.getReportRemark());
            hBean.setContrastsRemark(user.getOperatorName() + "医生已录入报告[" + dateTime + "]");
            AisStudyReportHEngine.save(hBean);
            //删除设备工作列表记录
            deleteModWorkList(sbean.getStudyAccnumber());
            //操作记录
            sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "HAVERPT");
            //上传临时报告
            String result = uploadReportTemp(reportBean, newReportId, request, imgArr,orgId);
            if(!"success".equals(result)){
            	throw new Exception("上传报告失败："+result);
            }
            return newReportId+"||"+result;
        }
        
    }

    //根据检查ID查报告信息
    public void deleteModWorkList(String studyAccnumber) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyAccnumber)) {
            condition.append(" AND accession_number = '" + studyAccnumber + "'");
        }
        AisModalityworklistBean[] mwBeans = AisModalityworklistEngine.getBeans(condition.toString(), null);
        if (mwBeans != null && mwBeans.length > 0) {
            for (AisModalityworklistBean mwBean : mwBeans) {
                mwBean.setStatus("1");
                AisModalityworklistEngine.save(mwBean);
            }
        }

    }

    /**
     *
     */
    public String getImgPrintTemp(String imgArr, long studyinfoId) throws Exception {
        Statement stmt = null;
        ResultSet rs  = null;
        String template = "";
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(studyinfoId);
        try {
            //3.2查html模板
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "  SELECT A.REPORT_FORMAT, B.ORG_ID, B.LOC_ID, B.MODALITY_ID "
                    + "  FROM AISC_REPORTFORMAT A, AISC_REPORTFORMAT2LOC B "
                    + "  WHERE A.FORMAT_ID = B.FORMAT_ID "
                    + "  AND ORG_ID = " + sbean.getOrgId() + " "
                    + "  AND LOC_ID = " + sbean.getLocId() + " "
                    + "  AND B.MODEL_TYPE = 2 "
                    //+"  AND MODALITY_ID = "+eBean.getModalityId()+" "
                    ;
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Clob reportFormat = rs.getClob("REPORT_FORMAT");
                if (reportFormat != null) {
                    template = reportFormat.getSubString(1, (int) reportFormat.length());
                    System.out.println("CLOB字段的值：" + template);
                }
            }
            if ("".equals(template)) {
                return "模板文件不存在，请确认！";
            }

        } catch (Exception e) {
            logger.error("查询影像模板信息异常：" + e.getMessage());
            String errMsg = e.getMessage().length() > 300 ? e.getMessage().substring(300) : e.getMessage();
            return "查询影像模板信息异常：" + errMsg;
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        //图片内容填充
        String patientImg = "";
        if (!"".equals(imgArr)) {
            imgArr = imgArr.replaceAll("@@", "&");
            String[] imgArray = imgArr.split(",");
            String html = "<tr>";
            for (int i = 0; i < imgArray.length; i++) {
                System.out.println(imgArray[i]);
                html += "<td align='center' height='1' valign='bottom' width='33%'><img border ='0' height='172' src='" + imgArray[i] + "' width='230' /><p align='center'>&nbsp;</p></td>";
                if (i != 0 && (i + 1) % 2 == 0) {
                    html += "</tr><tr>";
                }
            }
            html += "</tr>";
            patientImg = html;
        } else {
            patientImg = "";
        }
        //内容填充
        String studyReprotMsg = template.replaceAll("#ORG_NAME#", String.valueOf("咸阳第一人民医院"))
                .replaceAll("#图像#", String.valueOf(patientImg));
        return studyReprotMsg;
    }


    public String uploadReportTemp(AisStudyReportBean reportBean, long reportId, HttpServletRequest request, String imgArr,String orgId) throws Exception {
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(reportBean.getStudyinfoId());
        //3.生成html文件上传服务器
        //3.1首先取到设备类型ID
        String template = "";
        Statement stmt = null;
        ResultSet rs = null;
        String result = "success";
//        if (sbean.getStudyType()!=0&&sbean.getOrgId().equals(orgId)) {
//        	result = "false";
//        	throw new Exception("会诊报告不允修改");
//        }
        
        //按设备大类查模板现在不用
        AiscEquipmentBean eBean = AiscEquipmentEngine.getBean(sbean.getEquipmentId());
        //try {
            //3.2查html模板         //模板应该取当前登录所用的组织机构及科室标识查，还是直接用检查单中的组织机构和科室查
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "  SELECT A.REPORT_FORMAT, B.ORG_ID, B.LOC_ID, B.MODALITY_ID "
                    + "  FROM AISC_REPORTFORMAT A, AISC_REPORTFORMAT2LOC B "
                    + "  WHERE A.FORMAT_ID = B.FORMAT_ID ";
                    if(sbean.getStudyType()!=0){
                    	sql += "  AND ORG_ID = " + sbean.getStudyConsultorg() + " ";
                    	sql += "  AND LOC_ID = " + sbean.getStudyConsultloc()+ " ";
                    	sql += "  AND B.MODEL_TYPE = 3 ";
                    }else{
                    	sql += "  AND ORG_ID = " + sbean.getOrgId() + " ";
                    	sql += "  AND LOC_ID = " + sbean.getLocId() + " ";
                    	sql += "  AND MODALITY_ID = " + eBean.getModalityId() + "  AND B.MODEL_TYPE = 1 ";
                    } 
            rs = stmt.executeQuery(sql);

            String modalityId = "";
            if (rs.next()) {
                modalityId = rs.getString("MODALITY_ID");
                Clob reportFormat = rs.getClob("REPORT_FORMAT");
                if (reportFormat != null) {
                    template = reportFormat.getSubString(1, (int) reportFormat.length());
                    System.out.println("CLOB字段的值：" + template);
                }
            }
            if ("".equals(template)) {
                result = "打印模板文件不存在，请确认！";
                return result;
            } else {
                //3.3组装html文件
                //3.3.1获取填充数据
                Map map = getTemplateFilling(String.valueOf(reportBean.getStudyinfoId()));
                //剔除strike中的记录 留痕处理 -- 正则表达式
                String reportExam = String.valueOf(map.get("REPORT_EXAM"));
                String reportResult = String.valueOf(map.get("REPORT_RESULT"));
                Pattern p = Pattern.compile("<strike>.*</strike>");
                Matcher m1 = p.matcher(reportExam);
                Matcher m2 = p.matcher(reportResult);
                //去除删除标签
                String newReportExam = m1.replaceAll("");
                String newReportResult = m2.replaceAll("");

                //图片内容填充
                String patientImg = "";
                if (!"".equals(imgArr)) {
                    String[] imgArray = imgArr.split("\\|");
                    String html = "<tr>";
                    for (int i = 0; i < imgArray.length; i++) {
                        html += "<td align='center' height='1' valign='bottom' width='33%'><img border='0' height='172' src='" + imgArray[i] + "' width='230' /><p align='center'>&nbsp;</p></td>";
                        if (i != 0 && (i + 1) % 2 == 0) {
                            html += "</tr><tr>";
                        }
                    }
                    html += "</tr>";
                    patientImg = html;
                } else {
                    patientImg = "";
                }

                String id = "";
                String cardId = "";
                String cardNo = "";
                if ("INP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
                    id = "住院号";
                } else if ("HP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
                    id = "体检号";
                    cardId = "身份证号：";
                    cardNo = String.valueOf(map.get("PATIENT_IDNUMBER"));
                } else if ("OP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
                    id = "门诊号";
                } else {
                    id = "住院号";
                }
                //内容填充
                String studyReprotMsg = template.replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                        .replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                        .replaceAll("#TEMP#", "临时")
                        .replaceAll("#姓名#", String.valueOf(map.get("PATIENT_NAME")))
                        .replaceAll("#性别#", String.valueOf(map.get("PATIENT_SEX")))
                        .replaceAll("#年龄#", String.valueOf(map.get("PATIENT_AGE")))
                        .replaceAll("#检查号#", String.valueOf(map.get("STUDY_ACCNUMBER")))
                        .replaceAll("#仪器型号#", String.valueOf(map.get("EQUIPMENT_ID")))
                        .replaceAll("#来源#", String.valueOf(map.get("PATIENTTYPE")))
                        .replaceAll("#PATIENT_TYPEID#", id)
                        .replaceAll("#AUDITORFINAL_DOC#", "审核医师")
                        .replaceAll("#住院号#", String.valueOf(map.get("PATIENT_INPATIENTID")))
                        .replaceAll("#检查项目#", String.valueOf(map.get("STUDY_ITEMDESC")))
                        .replaceAll("#检查部位#", String.valueOf(map.get("STUDYITEM_BODYINFO")))
                        .replaceAll("#图像#", String.valueOf(patientImg))
                        .replaceAll("#审核日期#", String.valueOf(map.get("REPORT_VERIFYDATETIME")))
                        .replaceAll("#终审医生#", String.valueOf(map.get("REPORT_FINALDOCTORNAME")))
                        .replaceAll("#终审核期#", String.valueOf(map.get("REPORT_FINALDATETIME")))
                        .replaceAll("#报告医生#", String.valueOf(map.get("REPORT_DOCTORNAME")))
                        .replaceAll("#报告时间#", String.valueOf(map.get("REPORT_DATETIME")))
                        .replaceAll("#报告日期#", String.valueOf(map.get("REPORT_DATE")))
                        .replaceAll("#患者ID#", String.valueOf(map.get("PATIENT_ID")))
                        .replaceAll("#申请科室#", String.valueOf(map.get("STUDY_APPLOCNAME")))
                        .replaceAll("#检查日期#", String.valueOf(map.get("STUDY_TIME")))
                        .replaceAll("#门诊号#", String.valueOf(map.get("PATIENT_OUTPATIENTID")))
                        .replaceAll("#病区#", String.valueOf(map.get("STUDY_WARDNAME")))
                        .replaceAll("#床号#", String.valueOf(map.get("STUDY_BEDNO")))
                        .replaceAll("#房间#", String.valueOf(map.get("ROOM_ID")))
                        .replaceAll("#CARD_ID#", cardId)
                        .replaceAll("#身份证号#", cardNo)
                        .replaceAll("#检查所见#", newReportExam)
                        .replaceAll("#诊断意见#", newReportResult);
                if(sbean.getStudyType()!=0){
                	studyReprotMsg = studyReprotMsg.replaceAll("#审核医生#", String.valueOf(map.get("REPORT_VERIFYDOCTORNAME")));
                }
                //3.3.2生成文件上传服务器-保存文件表路径
                String fileAddr = "";
                long miId = 0l;
                logger.info("保存报告单到服务器START！");
                try {
                    // 生成html文件
                    String htmlHead = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
                    String htmlFoot = "</html>";
                    String script = "<link type='text/css' rel='stylesheet' href='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/pages/css/index/css/print.css'/><script type='text/javascript' src='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/common/js/jquery-1.11.2.js'></script><script type='text/javascript' language='javascript'>"
                            + "    function logout(){   "
                            + " var browserName=navigator.appName;	"
                            + " if (browserName=='Netscape'){	"
                            + "       window.open('', '_self', '');	"
                            + "       window.close();	"
                            + " }	"
                            + " if (browserName=='Microsoft Internet Explorer') {	"
                            + "       window.parent.opener = 'whocares'; 	"
                            + "       window.close(); 	"
                            + " }	"
                            + "}	"
                            + " function goBack(){ "
                            + " window.location.href = '" + com.ai.common.util.WebUtils.getBasePath(request) + "/workList/init.html'; "
                            + "if(!$('#menuTree',parent.document).is(':hidden')){ "
                            + "	parent.switchBarl(); "
                            + " } "
                            + " } "
                            + "function print(){	"
                            + " window.opener.document.getElementById('workbtnback').click();"
                            + "WebBrowser.ExecWB(6, 6);	"
                            //记录打印次数及打印状态
                            + "$.ajax({	"
                            + "    type: 'POST',	"
                            + "    async: true,	"
                            + "    url: '" + com.ai.common.util.WebUtils.getBasePath(request) + "/studyReport/setReportPrintInfo.ajax?reportId=" + reportId + "',"
                            + "    success: function (data) {setTimeout(function(){WebBrowser.execwb(45,1);},5000);	"
                            + "   "
                            + "    }"
                            + "  });"
                            + " }  "
                            + "   </script><style media=print>.page-content{display:none;}</style><OBJECT classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' id='WebBrowser' width='0'></OBJECT><div class='page-content' style='text-align:right'> 	"
                            + "<button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
                    String footBtn = "<div class='page-content' style='text-align:right'><button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
                    studyReprotMsg = htmlHead + script + studyReprotMsg + footBtn + htmlFoot;

                    if (studyReprotMsg != null && !"".equals(studyReprotMsg)) {

                        Timestamp t = SysdateManager.getSysTimestamp();
                        String date = DateUtils.getDateyyyymm(t);
                        String day = getDateday(t);
                        String time = DateUtils.getDateddmmmiss(t);

                        //按设备类型标识分目录存放
                        String mtype = eBean.getModalityId() + "";

                        // 生成一个随机数
                        Random rand = new Random();
                        String part2 = "";
                        for (int i = 0; i < 3; i++) {
                            part2 += rand.nextInt(10);
                        }

                        //文件存放服务器及路径处理
                        //1. 保存在哪个服务器，哪个介质下,根据科室取存储服务器IP及介质信息
                        QryServerMediumBean serverMedium = new QryServerMediumBean();
                        QryServerMediumBean[] serverMediums= null;
                        if(sbean.getStudyType()!=0){
                        	serverMediums = getServerMedium(String.valueOf(sbean.getStudyConsultloc()));
                        }else{
                        	serverMediums = getServerMedium(String.valueOf(sbean.getLocId()));
                        }
                        if (serverMediums.length > 0) {
                            for (QryServerMediumBean bean : serverMediums) {
                                if (bean.getMediumIsfull() == 0 || bean.getMediumIsonline() == 1) {
                                    //serverIp = bean.getServerIp();
                                    //mediumPath = bean.getMediumPath();
                                    serverMedium = bean;
                                    break;
                                }
                            }
                        } else {
                            result = "存储介质异常，请确认后再试!";
                            return result;
                        }
                        if ("".equals(serverMedium.getServerIp()) || "".equals(serverMedium.getMediumPath())) {
                            result = "存储介质异常，请确认后再试!";
                            return result;
                        }
                        //介质标识
                        miId = serverMedium.getMediumId();
                        //组织文件名
                        String fileName = date + "_" + time + "_" + part2;
                        //生成文件写到本地路径下
                        //String localPath = "C:/PACS_FILE"; //默认在C盘下
//						String localPath = request.getSession().getServletContext().getRealPath("/")+"PACS_FILE";
//
//						try{
//							WriteFile(localPath,fileName,studyReprotMsg);  //文件本地路径、文件名、文件内容
//						}catch (Exception e) {
//				            logger.error("写文件失败",e);
//				            result = "写文件失败，请稍后再试!";
//							return result;
//				        }
                        //将本地文件上传至服务器
                        logger.info("写文件开始...");
                        String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
                        File targetFile = new File(reportUpPath + fileName + ".html");

                        logger.info("写文件开始..." + reportUpPath + fileName + ".html");
                        if (!copy(studyReprotMsg, targetFile, reportUpPath)) {
                            result = "写HTML文件失败!";
                        }
//				        ftpUtil = new FTPSUtil();
//				        try{
//				            ftpUtil.connect(serverMedium.getServerIp(),Integer.parseInt(String.valueOf(serverMedium.getServerPort())), serverMedium.getServerUser(), serverMedium.getServerPassword());
//
//							Upload(ftpUtil, serverMedium.getMediumPath(), localPath, fileName);
//						}catch (Exception e) {
//							logger.error("上传文件失败",e);
//							result = "上传文件失败，请稍后再试!";
//							return result;
//				        }
//				        ftpUtil.closeChannel();
                        logger.info("写文件结束...");

                        //清空本地存放的文件
//				        logger.info("清空文件夹"+localPath);
//						deleteFile(new File(localPath));

                        //保存文件存放路径在文件表中
                        //fileAddr = serverMedium.getServerIp() + ":" + serverMedium.getServerPort()  + "/" + serverMedium.getMediumPath() + "/" + fileName +".html";
                        fileAddr = fileName + ".html";
//								if (!copy(studyReprotMsg, targetFile, targetDir)) {
//									result = "生成HTML文件失败!";
//									return result;
//								}else{
//									fileAddr = tempDir + targetFile.getName();
//								}
                    }
//					User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
//					Timestamp dateTime=new Timestamp(new Date().getTime());
                    //统一保存
//					AisStudyReportBean oldBean = new AisStudyReportBean();
//			        oldBean.setReportId(reportId);
//			        oldBean.setStsToOld();
//			        oldBean.setReportIscompleted(0);
//			        //oldBean.setReportDoctorid("1");
//			        oldBean.setReportVerifydatetime(dateTime);//报告审核日期
//			        oldBean.setReportVerifydoctorid(user.getCareProvId());//报告审核人
//			        oldBean.setReportFinaldatetime(dateTime);//终审日期
//			        oldBean.setReportFinaldoctorid(user.getCareProvId());//报告终审人
//			        oldBean.setReportRecord(user.getCareProvId());//报告录入者
//					DataContainerFactory.copyNoClearData(reportBean, oldBean);
//					AisStudyReportEngine.save(oldBean);

                    //记录文件地址到库表中 ---先删除原有保存报告单
                    AisReportFilesBean[] fileBeans = getfileBeans(reportId, "0");
                    if (fileBeans != null && fileBeans.length > 0) {
                        for (int i = 0; i < fileBeans.length; i++) {
                            AisReportFilesBean realBean = AisReportFilesEngine.getBean(fileBeans[i].getReportfileId());
                            realBean.setStsToOld();
                            realBean.delete();
                            AisReportFilesEngine.save(realBean);
                            AisFilesInfoBean oldFileBean = AisFilesInfoEngine.getBean(fileBeans[i].getFileId());
                            oldFileBean.setStsToOld();
                            oldFileBean.delete();
                            AisFilesInfoEngine.save(oldFileBean);
                        }
                    }

                    AisFilesInfoBean fileBean = new AisFilesInfoBean();
                    long fileId = ServiceUtil.getSequence("SEQFILEID");
                    fileBean.setFileId(fileId);
                    fileBean.setStudyinfoId(reportBean.getStudyinfoId());
                    fileBean.setFileType("html");
                    fileBean.setFilePath(fileAddr);
                    fileBean.setMiId(miId);
                    AisFilesInfoEngine.save(fileBean);

                    AisReportFilesBean reportFileBeqn = new AisReportFilesBean();
                    reportFileBeqn.setReportfileId(ServiceUtil.getSequence("SEQREPORTFILEID"));
                    reportFileBeqn.setFileId(fileId);
                    reportFileBeqn.setStatus("0");
                    reportFileBeqn.setReportId(reportId);
                    AisReportFilesEngine.save(reportFileBeqn);
                } catch (Exception e) {
                    result = "报告保存异常,请稍后再试：" + e.getMessage();
                    logger.error("报告保存异常,请稍后再试：" + e.getMessage());
                }
            }

        /*} catch (Exception e) {
            logger.error("查询报告模板信息异常：" + e.getMessage());
            String errMsg = e.getMessage().length() > 300 ? e.getMessage().substring(300) : e.getMessage();
            return "查询报告模板信息异常：" + errMsg;
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }*/

        return result;
    }

    //审核报告
    public String checkReport(AisStudyReportBean reportBean, HttpServletRequest request, String locId,
                              String studyConsultorg, String studyConsultloc, String imgAddrArray,String orgId,String consultRole) throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        Timestamp dateTime = new Timestamp(new Date().getTime());
        Statement stmt = null;
        ResultSet rs = null;
        //1.修改检查单状态为已审核
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(reportBean.getStudyinfoId());
        String result = "success";
        if (sbean.getStudyType()!=0&&sbean.getOrgId().equals(orgId)) {
        	result = "false";
        	throw new Exception("会诊申请方暂不允许书写报告");
        }
       
        if (sbean.getStudyType() == 1) {
        	//开关控制是否需要会诊结束
        	if("true".equals(consultRole)){
        		sbean.setStudystatusCode("Consulted"); //会诊记录为会诊结束状态
        	}else{
        		sbean.setStudystatusCode("VERIFY");
        	}
        } else {
            sbean.setStudystatusCode("VERIFY");
        }
        sbean.setStudyHavereport(1);
        if (!"-1".equals(studyConsultorg) && !"undefined".equals(studyConsultorg)) {
            sbean.setStudyConsultorg(studyConsultorg);
        }
        if (!"-1".equals(studyConsultloc) && !"undefined".equals(studyConsultloc) && !"".equals(studyConsultloc) && null != studyConsultloc) {
            sbean.setStudyConsultloc(Long.parseLong(studyConsultloc));
        }
        if (sbean.getStudyStarttime() == null || "".equals(sbean.getStudyStarttime())) {
            sbean.setStudyStarttime(dateTime);
        }
        //2.修改报告单状态为已完成
        AisStudyReportBean oldBean = AisStudyReportEngine.getBean(reportBean.getReportId());
        oldBean.setStsToOld();
        oldBean.setReportIscompleted(1); //报告未完成: 0 未完成,  1 完成
        oldBean.setReportVerifydatetime(dateTime);//报告审核日期
        oldBean.setReportVerifydoctorid(user.getCareProvId());//报告审核人
        oldBean.setReportRecord(user.getCareProvId());//报告录入者
        
        DataContainerFactory.copyNoClearData(reportBean, oldBean);
        if(oldBean.getReportToarchive()==1){
        	oldBean.setReportToarchive(2);
        }
        sbean.setStudyCreatetime(dateTime);
        //统一保存
        AisStudyInfoEngine.save(sbean);
        AisStudyReportEngine.save(oldBean);
        
        //3.生成html文件上传服务器
        //3.1首先取到设备类型ID
        String template = "";
        //按设备大类查模板现在不用
        AiscEquipmentBean eBean = AiscEquipmentEngine.getBean(sbean.getEquipmentId());
        //3.2查html模板         //模板应该取当前登录所用的组织机构及科室标识查，还是直接用检查单中的组织机构和科室查
        try{
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = "  SELECT A.REPORT_FORMAT, B.ORG_ID, B.LOC_ID, B.MODALITY_ID "
	                + "  FROM AISC_REPORTFORMAT A, AISC_REPORTFORMAT2LOC B "
	                + "  WHERE A.FORMAT_ID = B.FORMAT_ID ";
	                if(sbean.getStudyType()!=0){
	                	sql += "  AND ORG_ID = " + sbean.getStudyConsultorg() + " ";
	                	sql += "  AND LOC_ID = " + sbean.getStudyConsultloc()+ " ";
	                	sql += "  AND B.MODEL_TYPE = 3 ";
	                }else{
	                	sql += "  AND ORG_ID = " + sbean.getOrgId() + " ";
	                	sql += "  AND LOC_ID = " + sbean.getLocId() + " ";
	                	sql += "  AND MODALITY_ID = " + eBean.getModalityId() + "  AND B.MODEL_TYPE = 1 ";
	                } 
	        rs = stmt.executeQuery(sql);
	
	//            String modalityId = "";
	        if (rs.next()) {
	//                modalityId = rs.getString("MODALITY_ID");
	            Clob reportFormat = rs.getClob("REPORT_FORMAT");
	            if (reportFormat != null) {
	                template = reportFormat.getSubString(1, (int) reportFormat.length());
	                System.out.println("CLOB字段的值：" + template);
	            }
	        }
	        if ("".equals(template)) {
	        	result = "false";
	        	throw new Exception("审核报告失败：打印模板文件不存在，请确认！");
	        }
        }catch (Exception e) {
			// TODO: handle exception
		}
        finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }

        //3.3组装html文件
        //3.3.1获取填充数据
        Map map = getTemplateFilling(String.valueOf(reportBean.getStudyinfoId()));
        //剔除strike中的记录 留痕处理 -- 正则表达式
        String reportExam = String.valueOf(map.get("REPORT_EXAM"));
        String reportResult = String.valueOf(map.get("REPORT_RESULT"));
        Pattern p = Pattern.compile("<strike>.*</strike>");
        Matcher m1 = p.matcher(reportExam);
        Matcher m2 = p.matcher(reportResult);
        //去除删除标签
        String newReportExam = m1.replaceAll("");
        String newReportResult = m2.replaceAll("");

        //图片内容填充
        String patientImg = "";
        if (!"".equals(imgAddrArray)) {
            String[] imgArray = imgAddrArray.split("\\|");
            String html = "<table width='679' align='center'><tr>";
            for (int i = 0; i < imgArray.length; i++) {
                html += "<td align='center' valign='bottom'><img border='0' height='162' src='" + imgArray[i] + "' width='230' /><p align='center'>&nbsp;</p></td>";
                if (i != 0 && (i + 1) % 2 == 0) {
                    html += "</tr><tr>";
                }
            }
            html += "</tr></table>";
            patientImg = html;
        } else {
            patientImg = "";
        }
        if(!"".equals(patientImg)){
        	template = template.replace("<tr class=\"trc1\" height=\"297\" id=\"trc1\">","<tr class=\"trc1\" height=\"190\" id=\"trc1\">").
        			replace("<table border=\"0\" height=\"297\" width=\"679\">","<table border=\"0\" height=\"190\" width=\"679\">").
        			replace("<tr class=\"trc2\" height=\"248\" id=\"trc2\">","<tr class=\"trc2\" height=\"173\" id=\"trc2\">").
        			replace("<table border=\"0\" height=\"248\" width=\"679\">","<table border=\"0\" height=\"173\" width=\"679\">");
        }
        String id = "";
        String cardId = "";
        String cardNo = "";
        if ("INP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "住院号";
        } else if ("HP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "体检号";
            cardId = "身份证号：";
            cardNo = String.valueOf(map.get("PATIENT_IDNUMBER"));
        } else if ("OP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "门诊号";
        } else {
            id = "住院号";
        }
        //内容填充
        String studyReprotMsg = template.replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#TEMP#", "")
                .replaceAll("#姓名#", String.valueOf(map.get("PATIENT_NAME")))
                .replaceAll("#性别#", String.valueOf(map.get("PATIENT_SEX")))
                .replaceAll("#年龄#", String.valueOf(map.get("PATIENT_AGE")))
                .replaceAll("#检查号#", String.valueOf(map.get("STUDY_ACCNUMBER")))
                .replaceAll("#仪器型号#", String.valueOf(map.get("EQUIPMENT_ID")))
                .replaceAll("#来源#", String.valueOf(map.get("PATIENTTYPE")))
                .replaceAll("#PATIENT_TYPEID#", id)
                .replaceAll("#AUDITORFINAL_DOC#", "审核医师")
                .replaceAll("#住院号#", String.valueOf(map.get("PATIENT_INPATIENTID")))
                .replaceAll("#检查项目#", String.valueOf(map.get("STUDY_ITEMDESC")))
                .replaceAll("#检查部位#", String.valueOf(map.get("STUDYITEM_BODYINFO")))
                .replaceAll("#图像#", String.valueOf(patientImg))
                .replaceAll("#审核日期#", String.valueOf(map.get("REPORT_VERIFYDATETIME")))
                .replaceAll("#终审医生#", String.valueOf(map.get("REPORT_FINALDOCTORNAME")))
                .replaceAll("#终审核期#", String.valueOf(map.get("REPORT_FINALDATETIME")))
                .replaceAll("#报告医生#", String.valueOf(map.get("REPORT_DOCTORNAME")))
                .replaceAll("#报告时间#", String.valueOf(map.get("REPORT_DATETIME")))
                .replaceAll("#报告日期#", String.valueOf(map.get("REPORT_DATE")))
                .replaceAll("#患者ID#", String.valueOf(map.get("PATIENT_ID")))
                .replaceAll("#申请科室#", String.valueOf(map.get("STUDY_APPLOCNAME")))
                .replaceAll("#检查日期#", String.valueOf(map.get("STUDY_TIME")))
                .replaceAll("#门诊号#", String.valueOf(map.get("PATIENT_OUTPATIENTID")))
                .replaceAll("#病区#", String.valueOf(map.get("STUDY_WARDNAME")))
                .replaceAll("#床号#", String.valueOf(map.get("STUDY_BEDNO")))
                .replaceAll("#房间#", String.valueOf(map.get("ROOM_ID")))
                .replaceAll("#CARD_ID#", cardId)
                .replaceAll("#身份证号#", cardNo)
                .replaceAll("#检查所见#", newReportExam)
                .replaceAll("#诊断意见#", newReportResult);
//        if(sbean.getStudyType()!=0){
        	studyReprotMsg = studyReprotMsg.replaceAll("#审核医生#", String.valueOf(map.get("REPORT_VERIFYDOCTORNAME")));
//        }
        //3.3.2生成文件上传服务器-保存文件表路径
        String fileAddr = "";
        long miId = 0l;
        logger.info("保存报告单到服务器START！");
        try {
            // 生成html文件
            String htmlHead = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
            String htmlFoot = "</html>";
            String script = "<link type='text/css' rel='stylesheet' href='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/pages/css/index/css/print.css'/><script type='text/javascript' src='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/common/js/jquery-1.11.2.js'></script><script type='text/javascript' language='javascript'>"
                    + "    function logout(){   "
                    + " var browserName=navigator.appName;	"
                    + " if (browserName=='Netscape'){	"
                    + "       window.open('', '_self', '');	"
                    + "       window.close();	"
                    + " }	"
                    + " if (browserName=='Microsoft Internet Explorer') {	"
                    + "       window.parent.opener = 'whocares'; 	"
                    + "       window.close(); 	"
                    + " }	"
                    + "}	"
                    + " function goBack(){ "
                    + " window.location.href = '" + com.ai.common.util.WebUtils.getBasePath(request) + "/workList/init.html'; "
                    + "if(!$('#menuTree',parent.document).is(':hidden')){ "
                    + "	parent.switchBarl(); "
                    + " } "
                    + " } "
                    + "function print(){	"
                    + " window.opener.document.getElementById('workbtnback').click();"
                    + "WebBrowser.ExecWB(6, 6);	"
                    //记录打印次数及打印状态
                    + "$.ajax({	"
                    + "    type: 'POST',	"
                    + "    async: true,	"
                    + "    url: '" + com.ai.common.util.WebUtils.getBasePath(request) + "/studyReport/setReportPrintInfo.ajax?reportId=" + reportBean.getReportId() + "',"
                    + "    success: function (data) {setTimeout(function(){WebBrowser.execwb(45,1);},5000);	"
                    + "   "
                    + "    }"
                    + "  });"
                    + " }  "
                    + "   </script><style media=print>.page-content{display:none;}</style><OBJECT classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' id='WebBrowser' width='0'></OBJECT><div class='page-content' style='text-align:right'> 	"
                    + "<button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
            String footBtn = "<div class='page-content' style='text-align:right'><button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
            studyReprotMsg = htmlHead + script + studyReprotMsg + footBtn + htmlFoot;


            if (studyReprotMsg != null && !"".equals(studyReprotMsg)) {

                Timestamp t = SysdateManager.getSysTimestamp();
                String date = DateUtils.getDateyyyymm(t);
                String day = getDateday(t);
                String time = DateUtils.getDateddmmmiss(t);

                //按设备类型标识分目录存放
                String mtype = eBean.getModalityId() + "";

                // 生成一个随机数
                Random rand = new Random();
                String part2 = "";
                for (int i = 0; i < 3; i++) {
                    part2 += rand.nextInt(10);
                }

                //文件存放服务器及路径处理
                //1. 保存在哪个服务器，哪个介质下,根据科室取存储服务器IP及介质信息
                QryServerMediumBean serverMedium = new QryServerMediumBean();
                QryServerMediumBean[] serverMediums= null;
                if(sbean.getStudyType()!=0){
                	serverMediums = getServerMedium(String.valueOf(sbean.getStudyConsultloc()));
                }else{
                	serverMediums = getServerMedium(String.valueOf(sbean.getLocId()));
                }
                if (serverMediums.length > 0) {
                    for (QryServerMediumBean bean : serverMediums) {
                        if (bean.getMediumIsfull() == 0 || bean.getMediumIsonline() == 1) {
                            serverMedium = bean;
                            break;
                        }
                    }
                } else {
                	result = "false";
                	throw new Exception("上传报告失败：存储介质异常，请确认后再试!");
//                    return result;
                }
                if ("".equals(serverMedium.getServerIp()) || "".equals(serverMedium.getMediumPath())) {
                	result = "false";
                	throw new Exception("上传报告失败：存储介质异常，请确认后再试!");
//                    return result;
                }
                //介质标识
                miId = serverMedium.getMediumId();
                //组织文件名
                String fileName = date + "_" + time + "_" + part2;
                //生成文件写到本地路径下
                //String localPath = "C:/PACS_FILE"; //默认在C盘下
//				String localPath = request.getSession().getServletContext().getRealPath("/")+"PACS_FILE";
//				try{
//					WriteFile(localPath,fileName,studyReprotMsg);  //文件本地路径、文件名、文件内容
//				}catch (Exception e) {
//		            logger.error("写文件失败",e);
//		            result = "写文件失败，请稍后再试!";
//					return result;
//		        }
                //将本地文件写至fileServer服务器
                String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
                File targetFile = new File(reportUpPath + fileName + ".html");

                logger.info("写文件开始..." + reportUpPath + fileName + ".html");
                if (!copy(studyReprotMsg, targetFile, reportUpPath)) {
                	result = "false";
                	throw new Exception("上传报告失败：存储介质异常，写HTML文件失败!！");
                }
//		        ftpUtil = new FTPSUtil();
//		        try{
//		            ftpUtil.connect(serverMedium.getServerIp(),Integer.parseInt(String.valueOf(serverMedium.getServerPort())), serverMedium.getServerUser(), serverMedium.getServerPassword());
//
//					Upload(ftpUtil, serverMedium.getMediumPath(), localPath, fileName);
//				}catch (Exception e) {
//					logger.error("上传文件失败",e);
//					result = "上传文件失败，请稍后再试!";
//					return result;
//		        }
//		        ftpUtil.closeChannel();
                logger.info("写文件结束...");

                //清空本地存放的文件
//		        logger.info("清空文件夹"+localPath);
//				deleteFile(new File(localPath));

                //保存文件存放路径在文件表中
                //fileAddr = serverMedium.getServerIp() + ":" + serverMedium.getServerPort()  + "/" + serverMedium.getMediumPath() + "/" + fileName +".html";
                fileAddr = fileName + ".html";
//				if (!copy(studyReprotMsg, targetFile, targetDir)) {
//					result = "生成HTML文件失败!";
//					return result;
//				}else{
//					fileAddr = tempDir + targetFile.getName();
//				}
            }

            //记录文件地址到库表中   ----删除临时报告文件记录
            AisReportFilesBean[] fileBeans = getfileBeans(reportBean.getReportId(), "0");
            if (fileBeans != null && fileBeans.length > 0) {
                for (int i = 0; i < fileBeans.length; i++) {
                    AisReportFilesBean realBean = AisReportFilesEngine.getBean(fileBeans[i].getReportfileId());
                    realBean.setStsToOld();
                    realBean.delete();
                    AisReportFilesEngine.save(realBean);
                    AisFilesInfoBean oldFileBean = AisFilesInfoEngine.getBean(fileBeans[i].getFileId());
                    oldFileBean.setStsToOld();
                    oldFileBean.delete();
                    AisFilesInfoEngine.save(oldFileBean);
                }
            }

            AisFilesInfoBean fileBean = new AisFilesInfoBean();
            long fileId = ServiceUtil.getSequence("SEQFILEID");
            fileBean.setFileId(fileId);
            fileBean.setStudyinfoId(reportBean.getStudyinfoId());
            fileBean.setFileType("html");
            fileBean.setFilePath(fileAddr);
            fileBean.setMiId(miId);
            AisFilesInfoEngine.save(fileBean);

            AisReportFilesBean reportFileBeqn = new AisReportFilesBean();
            reportFileBeqn.setReportfileId(ServiceUtil.getSequence("SEQREPORTFILEID"));
            reportFileBeqn.setFileId(fileId);
            reportFileBeqn.setReportId(reportBean.getReportId());
            reportFileBeqn.setStatus("0");
            AisReportFilesEngine.save(reportFileBeqn);

        } catch (Exception e) {
            result = "审核报告保存异常,请稍后再试：" + e.getMessage();
            logger.error("审核报告保存异常,请稍后再试：" + e.getMessage());
        }
        
        if (sbean.getStudyType() == 1) {
        	if("true".equals(consultRole)){
        		 //操作记录
                sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Consulted");
                //消息队列记录
                sv.saveStudyMessage(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Consulted", sbean.getStudyType());
        	}else{
        		 //操作记录
                sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "VERIFY");
                //消息队列记录
                sv.saveStudyMessage(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "VERIFY", sbean.getStudyType());
        	}
        } else {
            //操作记录
            sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "VERIFY");
            //消息队列记录
            sv.saveStudyMessage(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "VERIFY", sbean.getStudyType());
          //记录到日志
            AisStudyReportHBean hBean[] = getReportHisBeans(reportBean.getReportId(), reportBean.getStudyinfoId(), "VERIFY");
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            AisStudyReportBean report = AisStudyReportEngine.getBean(reportBean.getReportId());
            if (hBean == null) {
                long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
                AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(reportBean.getReportId());
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportDatetime(report.getReportDatetime());
                newBean.setReportDoctorid(report.getReportDoctorid());
                newBean.setReportVerifydatetime(dateTime);//报告审核日期
                newBean.setReportVerifydoctorid(user.getCareProvId());//报告审核人
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setStatus("VERIFY");
                newBean.setReportNum(1);
                newBean.setContrastsRemark(user.getOperatorName() + "医生第一次审核报告[" + sdf.format(dateTime) + "]");
                AisStudyReportHEngine.save(newBean);
            } else {
                int count = getMaxReportNum(reportBean.getReportId(), reportBean.getStudyinfoId(), "VERIFY");
                long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
                AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(reportBean.getReportId());
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportDatetime(report.getReportDatetime());
                newBean.setReportDoctorid(report.getReportDoctorid());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setReportVerifydatetime(dateTime);//报告审核日期
                newBean.setReportVerifydoctorid(user.getCareProvId());//报告审核人
                newBean.setStatus("VERIFY");
                newBean.setReportNum((count + 1));
                newBean.setContrastsRemark(user.getOperatorName() + "医生第" + ToCH((count + 1)) + "次审核报告[" + sdf.format(dateTime) + "]");
                AisStudyReportHEngine.save(newBean);
            }
        }
        
        DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC", sbean.getOrgId());
        if (dictBean != null) {
            sysReportHisProc("V", sbean.getStudyAccnumber(), newReportExam, newReportResult, sbean.getOrgId());
        }
        
        //会诊记录审核后回传----中心端回传给申请方
        if(sbean.getStudyType() == 1){
        	String backUrl  = getBackUrl(sbean.getOrgId());
        	if(backUrl!=null){
        		RestTemplate restTemplate = ApplicationUtil.getBean("restTemplate",RestTemplate.class);
    			HttpHeaders headers = new HttpHeaders();
    			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    			headers.setContentType(type);
    			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    			headers.add("hzOrgId",sbean.getStudyConsultorg());
    			headers.add("studyAccnumber",sbean.getStudyAccnumber());
    			headers.add("studyTime",String.valueOf(map.get("STUDY_TIME")));
    			String reportDoc = URLEncoder.encode(getDoc(sbean.getStudyinfoId()).getReportDoctorname(), "UTF-8");
    			String verifyDoc = URLEncoder.encode(getDoc(sbean.getStudyinfoId()).getReportDoctorname(), "UTF-8");
    			headers.add("reportDoc",reportDoc);
    			headers.add("verifyDoc",verifyDoc);
    			headers.add("ipport",com.ai.common.util.WebUtils.getBasePath(request));
    			AisStudyReportData report = new AisStudyReportData();
    			report.setReportExam(newReportExam);
    			report.setReportResult(newReportResult);
    			CustomObjectMapper objectMapper = new CustomObjectMapper();
    			String json = objectMapper.writeValueAsString(report);
    			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
    			ResponseEntity<String> responseEntity = restTemplate.postForEntity(backUrl,entity, String.class);
    			String responseBody = responseEntity.getBody();
    			JSONObject jsonobject = JSONObject.fromObject(responseBody);
    			HzStudyBackResponse responseStu = (HzStudyBackResponse) JSONObject.toBean(jsonobject,HzStudyBackResponse.class);
    			if(!"0".equals(responseStu.getCode())){
    				throw new Exception("回传会诊报告失败："+responseStu.getMessage()+"！");
    			}
        	}else{
        		//throw new Exception("回传会诊报告失败：请配置回传会诊机构地址！");
        	}
        }
        return result;
    }
    
    public String retrivePatientImage(String patientId,String studyAccnumber,String orgId){
    	//实时影像上传接口
    	try{ 
    		FileServiceStub cilent = new FileServiceStub();
    		cilent._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
    		FileServiceStub.GPatientID gid = new FileServiceStub.GPatientID();
    		gid.setGPatientID(patientId);
    		FileServiceStub.AccessionNumber acc = new FileServiceStub.AccessionNumber();
    		acc.setAccessionNumber(studyAccnumber);
    		FileServiceStub.OrgCode orgcode = new FileServiceStub.OrgCode();
    		orgcode.setOrgCode("");
    		FileServiceStub.OrgId orgIds = new FileServiceStub.OrgId();
    		orgIds.setOrgId(orgId);
    		FileServiceStub.PatientID patientid = new FileServiceStub.PatientID();
    		patientid.setPatientID(patientId);
    		cilent._getServiceClient().getOptions().setTimeOutInMilliSeconds(5*60*1000);
    		logger.debug("中心端病人ID主键："+patientId+",检查号："+studyAccnumber+",机构ID："+orgId+",医院侧病人ID："+patientId);
    		UpFileResult result = cilent.retrivePatientImage(new FileServiceStub.UpFileInfo(), acc, gid, orgcode, orgIds, patientid);
    		return "0";
    	}catch(Exception e){
    		return "-1";
    	}
    }
    
    public QryReportBrowseBean getDoc(long studyinfoId) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" STUDYINFO_ID = "+studyinfoId);
        QryReportBrowseBean[] reportBeans = QryReportBrowseEngine.getBeans(condition.toString(), null);
        if (reportBeans!=null&&reportBeans.length > 0) {
            return reportBeans[0];
        } else {
            return null;
        }
    }
    
    private String getBackUrl(String orgId) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" AND ORG_ID = '"+ orgId+"'");
        }
        AisHzBackurlBean[] backurl = AisHzBackurlEngine.getBeans(condition.toString(), null);
        if (backurl.length > 0) {
            return backurl[0].getBackUrl();
        } else {
            return null;
        }
    }

    private void sysReportHisProc(String status, String studyAccnumber, String reportExam, String reportResult, String orgId) {
        Connection conn = null;
        try {
            conn = com.ai.aris.util.DAOUtils.getDBConnection(orgId, "");
            CallableStatement c = conn.prepareCall("{call p_SetReportInfo(?,?,?,?)}");
            c.setString(1, status);
            c.setString(2, studyAccnumber);
            c.setString(3, reportExam);
            c.setString(4, reportResult);
            c.execute();

        } catch (SQLException se) {
            logger.info("同步his p_SetReportInfo error:"
                    + se.toString());
            throw new com.ai.aris.util.DAOException(se.getMessage(), se);
        } finally {
            com.ai.aris.util.DAOUtils.closeConnection(conn);
        }
    }

    //二次审核报告
    public String checkFinalReport(AisStudyReportBean reportBean, HttpServletRequest request, String locId,
                                   String studyConsultorg, String studyConsultloc, String imgAddrArray,String orgId) throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        Timestamp dateTime = new Timestamp(new Date().getTime());
        //1.修改检查单状态为已审核
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(reportBean.getStudyinfoId());
        String result = "success";
        if (sbean.getStudyType()!=0&&sbean.getOrgId().equals(orgId)) {
        	result = "false";
        	throw new Exception("会诊申请方暂不允许书写报告");
        }
        if (sbean.getStudyType() == 1) {
            sbean.setStudystatusCode("Consulted"); //会诊记录为会诊结束状态
        } else {
            sbean.setStudystatusCode("Archived");
        }
        sbean.setStudyHavereport(1);
        if (!"-1".equals(studyConsultorg) && !"undefined".equals(studyConsultorg)) {
            sbean.setStudyConsultorg(studyConsultorg);
        }
        if (!"-1".equals(studyConsultloc) && !"undefined".equals(studyConsultloc) && !"".equals(studyConsultloc) && null != studyConsultloc) {
            sbean.setStudyConsultloc(Long.parseLong(studyConsultloc));
        }
        if (sbean.getStudyStarttime() == null || "".equals(sbean.getStudyStarttime())) {
            sbean.setStudyStarttime(dateTime);
        }

        //2.修改报告单状态为已完成
        AisStudyReportBean oldBean = AisStudyReportEngine.getBean(reportBean.getReportId());
        oldBean.setStsToOld();
        oldBean.setReportIscompleted(1); //报告未完成: 0 未完成,  1 完成
        oldBean.setReportFinaldatetime(dateTime);//终审日期
        oldBean.setReportFinaldoctorid(user.getCareProvId());//报告终审人
        oldBean.setReportRecord(user.getCareProvId());//报告录入者
        if(oldBean.getReportToarchive()==1){
        	oldBean.setReportToarchive(2);
        }
        DataContainerFactory.copyNoClearData(reportBean, oldBean);
        sbean.setStudyCreatetime(dateTime);
        //统一保存
        AisStudyInfoEngine.save(sbean);
        AisStudyReportEngine.save(oldBean);

        //3.生成html文件上传服务器
        //3.1首先取到设备类型ID
        String template = "";
        Statement stmt = null;
        ResultSet rs = null;
        //按设备大类查模板现在不用
        AiscEquipmentBean eBean = AiscEquipmentEngine.getBean(sbean.getEquipmentId());
        try{
	        //3.2查html模板         //模板应该取当前登录所用的组织机构及科室标识查，还是直接用检查单中的组织机构和科室查
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = "  SELECT A.REPORT_FORMAT, B.ORG_ID, B.LOC_ID, B.MODALITY_ID "
	                + "  FROM AISC_REPORTFORMAT A, AISC_REPORTFORMAT2LOC B "
	                + "  WHERE A.FORMAT_ID = B.FORMAT_ID ";
	                if(sbean.getStudyType()!=0){
	                	sql += "  AND ORG_ID = " + sbean.getStudyConsultorg() + " ";
	                	sql += "  AND LOC_ID = " + sbean.getStudyConsultloc()+ " ";
	                	sql += "  AND B.MODEL_TYPE = 3 ";
	                }else{
	                	sql += "  AND ORG_ID = " + sbean.getOrgId() + " ";
	                	sql += "  AND LOC_ID = " + sbean.getLocId() + " ";
	                	sql += "  AND MODALITY_ID = " + eBean.getModalityId() + "  AND B.MODEL_TYPE = 1 ";
	                } 
	                
	        rs = stmt.executeQuery(sql);
	
	//        String modalityId = "";
	        if (rs.next()) {
	//            modalityId = rs.getString("MODALITY_ID");
	            Clob reportFormat = rs.getClob("REPORT_FORMAT");
	            if (reportFormat != null) {
	                template = reportFormat.getSubString(1, (int) reportFormat.length());
	                System.out.println("CLOB字段的值：" + template);
	            }
	        }
	        if ("".equals(template)) {
	        	result = "false";
	        	throw new Exception("二次审核报告失败：打印模板文件不存在，请确认！");
	        }
    	}catch (Exception e) {
			// TODO: handle exception
		}finally{
	        DBUtil.release(rs, stmt, null);
	        ServiceManager.getSession().getConnection().close();
		}
        //3.3组装html文件
        //3.3.1获取填充数据
        Map map = getTemplateFilling(String.valueOf(reportBean.getStudyinfoId()));
        //剔除strike中的记录 留痕处理 -- 正则表达式
        String reportExam = String.valueOf(map.get("REPORT_EXAM"));
        String reportResult = String.valueOf(map.get("REPORT_RESULT"));
        Pattern p = Pattern.compile("<strike>.*</strike>");
        Matcher m1 = p.matcher(reportExam);
        Matcher m2 = p.matcher(reportResult);
        //去除删除标签
        String newReportExam = m1.replaceAll("");
        String newReportResult = m2.replaceAll("");

        //图片内容填充
        String patientImg = "";
        if (!"".equals(imgAddrArray)) {
            String[] imgArray = imgAddrArray.split("\\|");
            String html = "<tr>";
            for (int i = 0; i < imgArray.length; i++) {
                html += "<td align='center' height='1' valign='bottom' width='33%'><img border='0' height='172' src='" + imgArray[i] + "' width='230' /><p align='center'>&nbsp;</p></td>";
                if (i != 0 && (i + 1) % 2 == 0) {
                    html += "</tr><tr>";
                }
            }
            html += "</tr>";
            patientImg = html;
        } else {
            patientImg = "";
        }

        String id = "";
        String cardId = "";
        String cardNo = "";
        if ("INP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "住院号";
        } else if ("HP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "体检号";
            cardId = "身份证号：";
            cardNo = String.valueOf(map.get("PATIENT_IDNUMBER"));
        } else if ("OP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "门诊号";
        } else {
            id = "住院号";
        }
        //内容填充
        String studyReprotMsg = template.replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#TEMP#", "")
                .replaceAll("#姓名#", String.valueOf(map.get("PATIENT_NAME")))
                .replaceAll("#性别#", String.valueOf(map.get("PATIENT_SEX")))
                .replaceAll("#年龄#", String.valueOf(map.get("PATIENT_AGE")))
                .replaceAll("#检查号#", String.valueOf(map.get("STUDY_ACCNUMBER")))
                .replaceAll("#仪器型号#", String.valueOf(map.get("EQUIPMENT_ID")))
                .replaceAll("#来源#", String.valueOf(map.get("PATIENTTYPE")))
                .replaceAll("#PATIENT_TYPEID#", id)
                .replaceAll("#AUDITORFINAL_DOC#", "终审医师")
                .replaceAll("#住院号#", String.valueOf(map.get("PATIENT_INPATIENTID")))
                .replaceAll("#检查项目#", String.valueOf(map.get("STUDY_ITEMDESC")))
                .replaceAll("#检查部位#", String.valueOf(map.get("STUDYITEM_BODYINFO")))
                .replaceAll("#图像#", String.valueOf(patientImg))
                .replaceAll("#审核日期#", String.valueOf(map.get("REPORT_VERIFYDATETIME")))
                .replaceAll("#终审医生#", String.valueOf(map.get("REPORT_FINALDOCTORNAME")))
                .replaceAll("#终审核期#", String.valueOf(map.get("REPORT_FINALDATETIME")))
                .replaceAll("#报告医生#", String.valueOf(map.get("REPORT_DOCTORNAME")))
                .replaceAll("#报告时间#", String.valueOf(map.get("REPORT_DATETIME")))
                .replaceAll("#报告日期#", String.valueOf(map.get("REPORT_DATE")))
                .replaceAll("#患者ID#", String.valueOf(map.get("PATIENT_ID")))
                .replaceAll("#申请科室#", String.valueOf(map.get("STUDY_APPLOCNAME")))
                .replaceAll("#检查日期#", String.valueOf(map.get("STUDY_TIME")))
                .replaceAll("#门诊号#", String.valueOf(map.get("PATIENT_OUTPATIENTID")))
                .replaceAll("#病区#", String.valueOf(map.get("STUDY_WARDNAME")))
                .replaceAll("#床号#", String.valueOf(map.get("STUDY_BEDNO")))
                .replaceAll("#房间#", String.valueOf(map.get("ROOM_ID")))
                .replaceAll("#CARD_ID#", cardId)
                .replaceAll("#身份证号#", cardNo)
                .replaceAll("#检查所见#", newReportExam)
                .replaceAll("#诊断意见#", newReportResult);
        if(sbean.getStudyType()!=0){
        	studyReprotMsg = studyReprotMsg.replaceAll("#审核医生#", String.valueOf(map.get("REPORT_VERIFYDOCTORNAME")));
        }
        //3.3.2生成文件上传服务器-保存文件表路径
        String fileAddr = "";
        long miId = 0l;
        logger.info("保存报告单到服务器START！");
        try {
            // 生成html文件
            String htmlHead = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
            String htmlFoot = "</html>";
            String script = "<link type='text/css' rel='stylesheet' href='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/pages/css/index/css/print.css'/><script type='text/javascript' src='" + com.ai.common.util.WebUtils.getBasePath(request) + "/aris/statics/common/js/jquery-1.11.2.js'></script><script type='text/javascript' language='javascript'>"
                    + "    function logout(){   "
                    + " var browserName=navigator.appName;	"
                    + " if (browserName=='Netscape'){	"
                    + "       window.open('', '_self', '');	"
                    + "       window.close();	"
                    + " }	"
                    + " if (browserName=='Microsoft Internet Explorer') {	"
                    + "       window.parent.opener = 'whocares'; 	"
                    + "       window.close(); 	"
                    + " }	"
                    + "}	"
                    + " function goBack(){ "
                    + " window.location.href = '" + com.ai.common.util.WebUtils.getBasePath(request) + "/workList/init.html'; "
                    + "if(!$('#menuTree',parent.document).is(':hidden')){ "
                    + "	parent.switchBarl(); "
                    + " } "
                    + " } "
                    + "function print(){	"
                    + " window.opener.document.getElementById('workbtnback').click();"
                    + "WebBrowser.ExecWB(6, 6);	"
                    //记录打印次数及打印状态
                    + "$.ajax({	"
                    + "    type: 'POST',	"
                    + "    async: true,	"
                    + "    url: '" + com.ai.common.util.WebUtils.getBasePath(request) + "/studyReport/setReportPrintInfo.ajax?reportId=" + reportBean.getReportId() + "',"
                    + "    success: function (data) {setTimeout(function(){WebBrowser.execwb(45,1);},5000);	"
                    + "    }"
                    + "  });"
                    + " }  "
                    + "   </script><style media=print>.page-content{display:none;}</style><OBJECT classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' id='WebBrowser' width='0'></OBJECT><div class='page-content' style='text-align:right'> 	"
                    + "<button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
            String footBtn = "<div class='page-content' style='text-align:right'><button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
            studyReprotMsg = htmlHead + script + studyReprotMsg + footBtn + htmlFoot;

            if (studyReprotMsg != null && !"".equals(studyReprotMsg)) {

                Timestamp t = SysdateManager.getSysTimestamp();
                String date = DateUtils.getDateyyyymm(t);
                String day = getDateday(t);
                String time = DateUtils.getDateddmmmiss(t);

                //按设备类型标识分目录存放
                String mtype = eBean.getModalityId() + "";

                // 生成一个随机数
                Random rand = new Random();
                String part2 = "";
                for (int i = 0; i < 3; i++) {
                    part2 += rand.nextInt(10);
                }

                //文件存放服务器及路径处理
                //1. 保存在哪个服务器，哪个介质下,根据科室取存储服务器IP及介质信息
                QryServerMediumBean serverMedium = new QryServerMediumBean();
                QryServerMediumBean[] serverMediums= null;
                if(sbean.getStudyType()!=0){
                	serverMediums = getServerMedium(String.valueOf(sbean.getStudyConsultloc()));
                }else{
                	serverMediums = getServerMedium(String.valueOf(sbean.getLocId()));
                }
                if (serverMediums.length > 0) {
                    for (QryServerMediumBean bean : serverMediums) {
                        if (bean.getMediumIsfull() == 0 || bean.getMediumIsonline() == 1) {
                            //serverIp = bean.getServerIp();
                            //mediumPath = bean.getMediumPath();
                            serverMedium = bean;
                            break;
                        }
                    }
                } else {
                	result = "false";
                	throw new Exception("二次审核报告失败：存储介质异常，请确认后再试!");
                }
                if ("".equals(serverMedium.getServerIp()) || "".equals(serverMedium.getMediumPath())) {
                	result = "false";
                	throw new Exception("二次审核报告失败：存储介质异常，请确认后再试!");
                }
                //介质标识
                miId = serverMedium.getMediumId();
                //组织文件名
                String fileName = date + "_" + time + "_" + part2;
                //生成文件写到本地路径下
                //String localPath = "C:/PACS_FILE"; //默认在C盘下
//				String localPath = request.getSession().getServletContext().getRealPath("/")+"PACS_FILE";
//
//				try{
//					WriteFile(localPath,fileName,studyReprotMsg);  //文件本地路径、文件名、文件内容
//				}catch (Exception e) {
//		            logger.error("写文件失败",e);
//		            result = "写文件失败，请稍后再试!";
//					return result;
//		        }
                //将本地文件上传至服务器
                logger.info("写文件开始...");
                String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
                File targetFile = new File(reportUpPath + fileName + ".html");

                logger.info("写文件开始..." + reportUpPath + fileName + ".html");
                if (!copy(studyReprotMsg, targetFile, fileName)) {
                	result = "false";
                	throw new Exception("二次审核报告失败：写HTML文件失败!");
                }
//		        ftpUtil = new FTPSUtil();
//		        try{
//		            ftpUtil.connect(serverMedium.getServerIp(),Integer.parseInt(String.valueOf(serverMedium.getServerPort())), serverMedium.getServerUser(), serverMedium.getServerPassword());
//
//					Upload(ftpUtil, serverMedium.getMediumPath(), localPath, fileName);
//				}catch (Exception e) {
//					logger.error("上传文件失败",e);
//					result = "上传文件失败，请稍后再试!";
//					return result;
//		        }
//		        ftpUtil.closeChannel();
                logger.info("写文件结束...");

                //清空本地存放的文件
//		        logger.info("清空文件夹"+localPath);
//				deleteFile(new File(localPath));

                //保存文件存放路径在文件表中
                //fileAddr = serverMedium.getServerIp() + ":" + serverMedium.getServerPort()  + "/" + serverMedium.getMediumPath() + "/" + fileName +".html";
                fileAddr = fileName + ".html";
//					if (!copy(studyReprotMsg, targetFile, targetDir)) {
//						result = "生成HTML文件失败!";
//						return result;
//					}else{
//						fileAddr = tempDir + targetFile.getName();
//					}
            }

            //记录文件地址到库表中   ----删除临时报告文件记录
            AisReportFilesBean[] fileBeans = getfileBeans(reportBean.getReportId(), "1");
            if (fileBeans != null && fileBeans.length > 0) {
                for (int i = 0; i < fileBeans.length; i++) {
                    AisReportFilesBean realBean = AisReportFilesEngine.getBean(fileBeans[i].getReportfileId());
                    realBean.setStsToOld();
                    realBean.delete();
                    AisReportFilesEngine.save(realBean);
                    AisFilesInfoBean oldFileBean = AisFilesInfoEngine.getBean(fileBeans[i].getFileId());
                    oldFileBean.setStsToOld();
                    oldFileBean.delete();
                    AisFilesInfoEngine.save(oldFileBean);
                }
            }

            AisFilesInfoBean fileBean = new AisFilesInfoBean();
            long fileId = ServiceUtil.getSequence("SEQFILEID");
            fileBean.setFileId(fileId);
            fileBean.setStudyinfoId(reportBean.getStudyinfoId());
            fileBean.setFileType("html");
            fileBean.setFilePath(fileAddr);
            fileBean.setMiId(miId);
            AisFilesInfoEngine.save(fileBean);

            AisReportFilesBean reportFileBeqn = new AisReportFilesBean();
            reportFileBeqn.setReportfileId(ServiceUtil.getSequence("SEQREPORTFILEID"));
            reportFileBeqn.setFileId(fileId);
            reportFileBeqn.setReportId(reportBean.getReportId());
            reportFileBeqn.setStatus("1");
            AisReportFilesEngine.save(reportFileBeqn);

        } catch (Exception e) {
            result = "终审报告保存异常,请稍后再试：" + e.getMessage();
            logger.error("终审报告保存异常,请稍后再试：" + e.getMessage());
        }

        if (sbean.getStudyType() == 2) {
            //操作记录
            sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Consulted");
            //消息队列记录
            sv.saveStudyMessage(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Consulted", sbean.getStudyType());
        } else {
            //操作记录
            sv.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Archived");
            //消息队列记录
            sv.saveStudyMessage(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Archived", sbean.getStudyType());
            
          //记录到日志
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            AisStudyReportHBean hBean[] = getReportHisBeans(reportBean.getReportId(), reportBean.getStudyinfoId(), "Archived");
            AisStudyReportBean report = AisStudyReportEngine.getBean(reportBean.getReportId());
            if (hBean == null) {
                long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
                AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(reportBean.getReportId());
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportDoctorid(report.getReportDoctorid());
                newBean.setReportDatetime(report.getReportDatetime());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setReportVerifydoctorid(report.getReportVerifydoctorid());
                newBean.setReportVerifydatetime(report.getReportVerifydatetime());
                newBean.setReportFinaldatetime(dateTime);//报告终审日期
                newBean.setReportFinaldoctorid(user.getCareProvId());//报告终审人
                newBean.setStatus("Archived");
                newBean.setReportNum(1);
                newBean.setContrastsRemark(user.getOperatorName() + "医生第一次终审报告[" + sdf.format(dateTime) + "]");
                AisStudyReportHEngine.save(newBean);
            } else {
                int count = getMaxReportNum(reportBean.getReportId(), reportBean.getStudyinfoId(), "Archived");
                long historyId = ServiceUtil.getSequence("SEQREPORTHIS_ID");
                AisStudyReportHBean newBean = new AisStudyReportHBean();
                newBean.setReportHistoryId(historyId);
                newBean.setReportId(reportBean.getReportId());
                newBean.setStudyinfoId(reportBean.getStudyinfoId());
                newBean.setReportExam(reportBean.getReportExam());
                newBean.setReportResult(reportBean.getReportResult());
                newBean.setReportIspositive(reportBean.getReportIspositive());
                newBean.setReportDoctorid(report.getReportDoctorid());
                newBean.setReportDatetime(report.getReportDatetime());
                newBean.setReportRecord(user.getCareProvId());
                newBean.setReportRemark(reportBean.getReportRemark());
                newBean.setReportVerifydoctorid(report.getReportVerifydoctorid());
                newBean.setReportVerifydatetime(report.getReportVerifydatetime());
                newBean.setReportFinaldatetime(dateTime);//报告终审日期
                newBean.setReportFinaldoctorid(user.getCareProvId());//报告终审人
                newBean.setStatus("Archived");
                newBean.setReportNum((count + 1));
                newBean.setContrastsRemark(user.getOperatorName() + "医生第" + ToCH((count + 1)) + "次终审报告[" + sdf.format(dateTime) + "]");
                AisStudyReportHEngine.save(newBean);
            }
        }

        DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC", sbean.getOrgId());
        if (dictBean != null) {
            sysReportHisProc("V", sbean.getStudyAccnumber(), newReportExam, newReportResult, sbean.getOrgId());
        }

        return result;
    }


    public String uploadImg(String locId, HttpServletRequest request, long newRreportId, AisStudyReportBean reportBean, String imgArr, String studyinfoId) throws Exception {
        String result = "";
        QryRegInfoBean regInfoBean = sv.getRegInfo(studyinfoId);
        //文件存放服务器及路径处理
        //1. 保存在哪个服务器，哪个介质下,根据科室取存储服务器IP及介质信息
        QryServerMediumBean serverMedium = new QryServerMediumBean();
        QryServerMediumBean[] serverMediums = getServerMedium(locId);
        if (serverMediums.length > 0) {
            for (QryServerMediumBean bean : serverMediums) {
                if (bean.getMediumIsfull() == 0 || bean.getMediumIsonline() == 1) {
                    serverMedium = bean;
                    break;
                }
            }
        } else {
            result = "存储介质异常，请确认后再试!";
            return result;
        }
        if ("".equals(serverMedium.getServerIp()) || "".equals(serverMedium.getMediumPath())) {
            result = "存储介质异常，请确认后再试!";
            return result;
        }
        //介质标识
        long miId = serverMedium.getMediumId();
        //组织文件名
        imgArr = imgArr.replaceAll("@@", "&");
        String arr[] = imgArr.split(",");
        for (int i = 0; i < arr.length; i++) {
            String imgName = arr[i].substring(arr[i].indexOf("?") + 9, arr[i].indexOf("&"));
//			String imgPath = arr[i].substring(arr[i].indexOf("&")+9);
            String dir = regInfoBean.getPatientId() + "_" + regInfoBean.getStudyAccnumber();
            String path = "C:/Windows/AIPACS/Forumpath/" + dir;

            //判断已上传的图片不再上传
            AisFilesInfoBean oldBean = getAisFilesInfo(studyinfoId, imgName, miId);
            if (oldBean == null) {
                //将本地文件上传至服务器
                logger.info("上传文件开始...");
                ftpUtil = new FTPSUtil();
                try {
                    ftpUtil.connect(serverMedium.getServerIp(), Integer.parseInt(String.valueOf(serverMedium.getServerPort())), serverMedium.getServerUser(), serverMedium.getServerPassword());

                    UploadImg(ftpUtil, "/STUDY_REPORT_IMGFILE/" + dir, path, imgName);
                } catch (Exception e) {
                    logger.error("上传影像文件失败", e);
                    result = "上传影像文件失败，请稍后再试!";
                    return result;
                }

                ftpUtil.closeChannel();
                logger.info("上传影像文件结束...");
                try {
                    //记录文件地址到库表中
                    AisFilesInfoBean fileBean = new AisFilesInfoBean();
                    long fileId = ServiceUtil.getSequence("SEQFILEID");
                    fileBean.setFileId(fileId);
                    fileBean.setStudyinfoId(reportBean.getStudyinfoId());
                    fileBean.setFileType("JPG");
                    fileBean.setFilePath(imgName);
                    fileBean.setMiId(miId);
                    AisFilesInfoEngine.save(fileBean);

                    AisReportFilesBean reportFileBeqn = new AisReportFilesBean();
                    reportFileBeqn.setReportfileId(ServiceUtil.getSequence("SEQREPORTFILEID"));
                    reportFileBeqn.setFileId(fileId);
                    reportFileBeqn.setReportId(reportBean.getReportId());
                    AisReportFilesEngine.save(reportFileBeqn);
                } catch (Exception e) {
                    result = "影像上传异常,请稍后再试：" + e.getMessage();
                    logger.error("影像上传异常,请稍后再试：" + e.getMessage());
                }
            }
        }
        return "";
    }

    private AisFilesInfoBean getAisFilesInfo(String studyinfoId, String pathName, long miId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        if (isNotBlank(pathName)) {
            condition.append(" AND FILE_PATH = '" + pathName + "'");
        }
        if (isNotBlank(pathName)) {
            condition.append(" AND MI_ID = " + miId);
        }
        condition.append(" and FILE_TYPE='JPG' ");
        AisFilesInfoBean[] bean = AisFilesInfoEngine.getBeans(condition.toString(), null);

        if (bean.length > 0) {
            return bean[0];
        }
        return null;
    }

    //上传文件到FTP服务器
    private static void UploadImg(FTPSUtil ftpUtil, String directory, String path, String filename) throws Exception {
        if (!"".equals(directory)) {
            ftpUtil.makeDirectoryNoEnter(directory);
        }
        ftpUtil.upload(directory, path + "/" + filename, filename + ".tmp");
        ftpUtil.renameWithOutDir(directory + "/" + filename + ".tmp", directory + "/" + filename);
    }

    @SuppressWarnings("unchecked")
    public Map getTemplateFilling(String studyinfoId) throws Exception {
        logger.info("studyinfoId:::::::::::::::" + studyinfoId);
        Map map = new HashMap();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();

            String sql = "   SELECT B.STUDYINFO_ID, "
                    + "    B.ORG_ID, "
                    + "    B.LOC_ID, "
                    + "    A.patient_idnumber, "
                    + "    (SELECT SO.ORG_NAME FROM SYS_ORG SO WHERE SO.ORG_ID = B.ORG_ID) AS ORG_NAME, "
                    + "    B.STUDY_ACCNUMBER, "
                    + "    B.PATIENT_GLOBALID, "
                    + "    A.PATIENT_NAME, "
                    + "    DECODE(A.PATIENT_SEX, '1', '男', '2', '女', '未知') PATIENT_SEX, "
                    + "    B.PATIENT_AGE, "
                    + "    B.STUDY_WARD, "
                    + "    B.STUDY_BEDNO, "
                    + "    B.STUDY_ITEMDESC STUDY_ITEMDESC,  "
                    + "    (SELECT WM_CONCAT(C.STUDYITEM_BODYINFO) "
                    + "      FROM AIS_STUDYITEMINFO C "
                    + "      WHERE B.STUDYINFO_ID = C.STUDYINFO_ID) STUDYITEM_BODYINFO, "
                    + "    B.STUDY_REMARK, "
                    + "    B.STUDY_CLINIC, "
                    + "    B.PATIENT_INPATIENTID, "
                    + "    B.STUDY_DOCTORID, "
                    + "    B.AID_DOCTORID, "
                    + "    B.STUDY_EXPOSURECOUNT, "
                    + "    B.STUDY_FILMCOUNT, "
                    + "    B.STUDY_HAVEREPORT, "
                    + "    B.STUDY_HAVEIMAGE, "
                    + "    TO_CHAR(B.STUDY_STARTTIME, 'YYYY-MM-DD hh24:mi:ss') STUDY_TIME, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = B.STUDY_DOCTORID) STUDY_DOCTORNAME, "
                    + "    B.EQUIPMENT_ID, "
                    + "    (SELECT SD.ITEM_NAME FROM SYS_DICTITEM SD WHERE SD.ITEM_NO = B.PATIENTTYPE_CODE AND DICT_NAME='PATIENT_TYPE') PATIENTTYPE, "
                    + "    B.PATIENTTYPE_CODE, "
                    + "    C.REPORT_EXAM, "
                    + "    C.REPORT_RESULT, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_DOCTORID) REPORT_DOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_DATETIME, 'YYYY-MM-DD hh24:mi:ss') REPORT_DATETIME, "
                    + "    TO_CHAR(C.REPORT_DATETIME, 'YYYY-MM-DD hh24:mi:ss') REPORT_DATE, "
                    + "    B.STUDY_ITEMDESC, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_VERIFYDOCTORID) REPORT_VERIFYDOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_VERIFYDATETIME, 'YYYY-MM-DD') REPORT_VERIFYDATETIME, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_FINALDOCTORID) REPORT_FINALDOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_FINALDATETIME, 'YYYY-MM-DD') REPORT_FINALDATETIME, "
                    + "    A.PATIENT_ID , "
                    + "    (SELECT AL.LOC_DESC FROM AISC_LOC AL WHERE AL.LOC_ID = B.STUDY_APPLOCID AND AL.ORG_ID = B.ORG_ID AND LOC_TYPE='A')  STUDY_APPLOCNAME , "
                    + "    (SELECT AL.LOC_DESC FROM AISC_LOC AL WHERE AL.LOC_ID = B.STUDY_WARD AND AL.ORG_ID = B.ORG_ID AND LOC_TYPE='B')  STUDY_WARDNAME , "
                    + "    B.PATIENT_OUTPATIENTID , "
                    + "    B.STUDY_BEDNO , "
                    + "    B.ROOM_ID  "
                    + " FROM AIS_PATIENTINFO   A, "
                    + "    AIS_STUDYINFO     B,  "
                    + "    AIS_STUDYREPORT   C "
                    + " WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID "
                    + " AND B.STUDYINFO_ID = C.STUDYINFO_ID "
                    + " AND B.STUDYINFO_ID = " + studyinfoId + " ";
            rs = stmt.executeQuery(sql);
            logger.info("studyinfoId--2:::::::::::::::" + sql);
            if (rs.next()) {
                map.put("ORG_NAME", rs.getString("ORG_NAME") == null ? "" : rs.getString("ORG_NAME"));
                map.put("PATIENT_NAME", rs.getString("PATIENT_NAME") == null ? "" : rs.getString("PATIENT_NAME"));
                map.put("PATIENT_SEX", rs.getString("PATIENT_SEX") == null ? "" : rs.getString("PATIENT_SEX"));
                map.put("PATIENT_AGE", rs.getString("PATIENT_AGE") == null ? "" : rs.getString("PATIENT_AGE"));
                map.put("STUDY_ACCNUMBER", rs.getString("STUDY_ACCNUMBER") == null ? "" : rs.getString("STUDY_ACCNUMBER"));
                map.put("EQUIPMENT_ID", rs.getString("EQUIPMENT_ID") == null ? "" : rs.getString("EQUIPMENT_ID"));
                map.put("PATIENTTYPE", rs.getString("PATIENTTYPE") == null ? "" : rs.getString("PATIENTTYPE"));
                map.put("PATIENT_INPATIENTID", rs.getString("PATIENT_INPATIENTID") == null ? "" : rs.getString("PATIENT_INPATIENTID"));
                map.put("STUDYITEM_BODYINFO", rs.getString("STUDYITEM_BODYINFO") == null ? "" : rs.getString("STUDYITEM_BODYINFO"));
                map.put("STUDY_TIME", rs.getString("STUDY_TIME") == null ? "" : rs.getString("STUDY_TIME"));
                map.put("STUDY_DOCTORNAME", rs.getString("STUDY_DOCTORNAME") == null ? "" : rs.getString("STUDY_DOCTORNAME"));

                map.put("REPORT_DOCTORNAME", rs.getString("REPORT_DOCTORNAME") == null ? "" : rs.getString("REPORT_DOCTORNAME"));
                map.put("REPORT_DATETIME", rs.getString("REPORT_DATETIME") == null ? "" : rs.getString("REPORT_DATETIME"));
                map.put("REPORT_DATE", rs.getString("REPORT_DATE") == null ? "" : rs.getString("REPORT_DATE"));
                map.put("STUDY_ITEMDESC", rs.getString("STUDY_ITEMDESC") == null ? "" : rs.getString("STUDY_ITEMDESC"));
                map.put("REPORT_VERIFYDOCTORNAME", rs.getString("REPORT_VERIFYDOCTORNAME") == null ? "" : rs.getString("REPORT_VERIFYDOCTORNAME"));
                map.put("REPORT_VERIFYDATETIME", rs.getString("REPORT_VERIFYDATETIME") == null ? "" : rs.getString("REPORT_VERIFYDATETIME"));
                map.put("REPORT_FINALDOCTORNAME", rs.getString("REPORT_FINALDOCTORNAME") == null ? "" : rs.getString("REPORT_FINALDOCTORNAME"));
                map.put("REPORT_FINALDATETIME", rs.getString("REPORT_FINALDATETIME") == null ? "" : rs.getString("REPORT_FINALDATETIME"));
                map.put("PATIENT_ID", rs.getString("PATIENT_ID") == null ? "" : rs.getString("PATIENT_ID"));
                map.put("STUDY_APPLOCNAME", rs.getString("STUDY_APPLOCNAME") == null ? "" : rs.getString("STUDY_APPLOCNAME"));
                map.put("PATIENTTYPE_CODE", rs.getString("PATIENTTYPE_CODE") == null ? "" : rs.getString("PATIENTTYPE_CODE"));

                map.put("STUDY_WARDNAME", rs.getString("STUDY_WARDNAME") == null ? "" : rs.getString("STUDY_WARDNAME"));
                map.put("PATIENT_OUTPATIENTID", rs.getString("PATIENT_OUTPATIENTID") == null ? "" : rs.getString("PATIENT_OUTPATIENTID"));
                map.put("STUDY_BEDNO", rs.getString("STUDY_BEDNO") == null ? "" : rs.getString("STUDY_BEDNO"));
                map.put("ROOM_ID", rs.getString("ROOM_ID") == null ? "" : rs.getString("ROOM_ID"));
                map.put("PATIENT_IDNUMBER", rs.getString("PATIENT_IDNUMBER") == null ? "" : rs.getString("PATIENT_IDNUMBER"));

                Clob exam = rs.getClob("REPORT_EXAM");//检查所见
                Clob result = rs.getClob("REPORT_RESULT");//诊断结果
                if (exam != null) {
                    map.put("REPORT_EXAM", exam.getSubString(1, (int) exam.length()) == null ? "" : exam.getSubString(1, (int) exam.length()));
                }
                if (result != null) {
                    map.put("REPORT_RESULT", result.getSubString(1, (int) result.length()) == null ? "" : result.getSubString(1, (int) result.length()));
                }
                logger.info("studyinfoId--3:::::::::::::::" + map.get("REPORT_EXAM").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取模板填充值异常：" + e.getMessage());
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }

        return map;
    }

    //将模板内容copy到对应目录文件中
    public static boolean copy(String src, File dest, String filePath) {
        try {
            FileOutputStream out = null;
            try {
                File fileDir = new File(filePath);
                if (!fileDir.isDirectory()) {
                    //fileDir.mkdirs();
                }
                out = new FileOutputStream(dest);
                out.write(src.getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error("没找到具体的文件，异常：" + e.getMessage());
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IO异常" + e.getMessage());
                return false;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //根据检查ID查报告信息
    public AisStudyReportBean getReport(String studyinfoId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        AisStudyReportBean[] ruleBean = AisStudyReportEngine.getBeans(condition.toString(), null);

        if (ruleBean.length > 0) {
            return ruleBean[0];
        } else {
            return null;
        }
    }
    
    public AisFilesInfoBean[]  getFileInfo(String studyinfoId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        condition.append(" AND file_type ='PDF' ");
        AisFilesInfoBean[] fileInfo = AisFilesInfoEngine.getBeans(condition.toString(), null);

        if (fileInfo.length > 0) {
            return fileInfo;
        } else {
            return null;
        }
    }
    
    /**
     * ai数据上传------供影像浏览器调用
     */
    public JSONObject sendStudyinfoUp(String studyaccNumber,String seriesNo,String seriesUid) throws Exception{
    	//影像浏览器调用前先上传检查记录
    	
    	JSONObject jsonObj = new JSONObject();
    	String imgUrl = PropertiesUtils.getContextProperty("DCMImageUrl");
    	String ai_selfQry_url = PropertiesUtils.getContextProperty("ai_selfQry_url");
    	String ai_selfSave_url = PropertiesUtils.getContextProperty("ai_selfSave_url");
    	String ai_repeatUpload_url = PropertiesUtils.getContextProperty("ai_repeatUpload_url");
    	String ai_partnerId = PropertiesUtils.getContextProperty("ai_partnerId");
    	String ai_token = PropertiesUtils.getContextProperty("ai_token");
    	
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	HttpHeaders headers = new HttpHeaders();
    	MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    	headers.setContentType(type);
    	headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        RestTemplate restTemplate = new RestTemplate();
        AisStudyInfoBean studyInfo = getStudyInfoByAccnumber(studyaccNumber);
        
        QryResultRequest result = new QryResultRequest();
        result.setStudyId(String.valueOf(studyInfo.getStudyinfoId()));
        result.setOrgId(studyInfo.getOrgId());
        String json = JsonUtil.toJson(result);
        long st=System.currentTimeMillis();
		Timestamp stime=new Timestamp(System.currentTimeMillis());
		
        HttpEntity<QryResultRequest> entity = new HttpEntity<QryResultRequest>(result, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ai_selfQry_url, entity, String.class);
        String responseBody = responseEntity.getBody();
        JSONObject jsonobject = JSONObject.fromObject(responseBody);
        QryResultResponse response = (QryResultResponse) JSONObject.toBean(jsonobject,QryResultResponse.class);
        this.addLog(stime,st,"影像浏览器调用平台结果接口",response.getCode(),json.substring(0,json.length()>3000?3000:json.length()),JsonUtil.toJson(response),"");
        //首先判断平台是否有结果数据
        if("0000".equals(response.getCode())){
        	//检查信息数据上传到AI
        	if("1".equals(response.getData().getStatus())){
        		List<ImagesBean> list = new ArrayList<ImagesBean>();
        		List<ImagesBean> subList = new ArrayList<ImagesBean>();
				QryImageForAIBean[] images = QryImageForAIEngine.getBeans("  accessionnumber='"+studyaccNumber+"' and seriesuid='"+seriesUid+"' ",null);
				if(images!=null&&images.length>0){
        			for(QryImageForAIBean im:images){
        				ImagesBean image = new ImagesBean();
                		image.setImageId(im.getSopinstanceuid());
                		image.setUrl("");
                		image.setDescPosition("0");
                		String base64Img = encodeBase64File(imgUrl+im.getStudyuid()+"/"+im.getSeriesuid()+"/"+im.getDcmrogfilename());
                		image.setContent(base64Img);
                		list.add(image);
        			}
        		}
				int pagesize = 8;
				int totalcount = images.length; 
				int m = totalcount % pagesize; 
				int pagecount = 0;  
				if(m > 0){  
		            pagecount = totalcount / pagesize + 1;  
		        }else{  
		            pagecount = totalcount / pagesize;  
		        } 
				for(int i = 1; i <= pagecount; i++) {  
					if (m == 0) {  
		                subList = list.subList((i - 1) * pagesize, pagesize * (i));  
		                System.out.println("aaaaaaaaaaa"+subList);  
		            }else{  
		                if(i == pagecount){  
		                    subList = list.subList((i - 1) * pagesize, totalcount);  
		                    logger.debug("----------------第"+i+"次传输"+(i - 1) * pagesize+"到"+totalcount+"影像-----------------------");
		                }else{  
		                    subList = list.subList((i - 1) * pagesize, pagesize * (i)); 
		                    logger.debug("----------------第"+i+"次传输"+(i - 1) * pagesize+"到"+pagesize * (i)+"影像-----------------------");
		                }  
		            }
					AisPatientInfoBean patientBean = AisPatientInfoEngine.getBean(studyInfo.getPatientGlobalid());
	        		UploadDicomRequest request = new UploadDicomRequest();
	        		request.setStudyId(String.valueOf(studyInfo.getStudyinfoId()));
	        		request.setStudyName(studyInfo.getStudyItemdesc());
	        		request.setStudyType("4");
	        		request.setSeriesNo(seriesNo);
	        		if(i==pagecount){
	        			request.setUploadDone("1");
	        		}else{
	        			request.setUploadDone("0");
	        		}
	        		
	        		request.setPatientName(patientBean.getPatientName());
	        		request.setImages(subList);
	        		String requestBody = JsonUtil.toJson(request);
	        		DateFormat sdfuid = new SimpleDateFormat("yyyyMMdd");
	        		Timestamp dateTime=new Timestamp(new Date().getTime());
	        		String uuid = sdfuid.format(dateTime)+dateTime.getTime();
	    	    	String aiResponse = getHttpResponse(ai_repeatUpload_url,requestBody,ai_partnerId,ai_token,uuid);
	    	    	JSONObject responseObj = JSONObject.fromObject(aiResponse);
	    	    	UploadDicomResponse responses = (UploadDicomResponse) JSONObject.toBean(responseObj,UploadDicomResponse.class);
	    	    	long st1=System.currentTimeMillis();
	    			Timestamp stime1=new Timestamp(System.currentTimeMillis());
	    	    	this.addLog(stime1,st1,"影像浏览器调用上传接口",responses.getCode(),"UUID:"+uuid+",studyinfoId:"+request.getStudyId()+",serialNo:"+request.getSeriesNo(),JsonUtil.toJson(responses),"");
	    	    	logger.debug("----------------第"+i+"次传输结果----"+responses.getCode()+",---是否完整--"+request.getUploadDone());
	    	    	if("0".equals(responses.getCode())&&"1".equals(request.getUploadDone())){
	    	    		 //新增平台检查信息结果表
	    	    		logger.debug("----------------新增平台检查信息结果表------------");
	    	    		AisAiDiagnosisModel model = new AisAiDiagnosisModel();
	    	    		model.setStudyId(String.valueOf(studyInfo.getStudyinfoId()));
	                    model.setOrgId(studyInfo.getOrgId());
	                    model.setStudyDate(sdf.format(studyInfo.getStudyStarttime()));
	                    model.setStudyName(URLEncoder.encode(studyInfo.getStudyItemdesc(),"UTF-8"));
	                    model.setStudyType("4");
	                    model.setPatientId(patientBean.getPatientId());
	                    model.setPatientName(URLEncoder.encode(patientBean.getPatientName(),"UTF-8"));
	                    model.setPatientGender(patientBean.getPatientSex());
	                    model.setPatientBirthday(sdf.format(patientBean.getPatientDob()));
	                    model.setUploadTime(dateTime) ;
	                    model.setImgNum(String.valueOf(images.length));
	                    model.setCode(responses.getCode());
	                    model.setMessage(responses.getMsg());
	    	            String jsonModel = JsonUtil.toJson(model);
	    	            HttpEntity<String> entity1 = new HttpEntity<String>(jsonModel, headers);
	    	            ResponseEntity<String> responseEntity1 = restTemplate.postForEntity(ai_selfSave_url, entity1, String.class);
	    	            String responseBody1 = responseEntity1.getBody();
	    	            JSONObject resultBody = JSONObject.fromObject(responseBody1);
	    	            AIQryResultResponse resultResponse = (AIQryResultResponse) JSONObject.toBean(resultBody,AIQryResultResponse.class);
	    	            long st2=System.currentTimeMillis();
	    	    		Timestamp stime2=new Timestamp(System.currentTimeMillis());
	    	            this.addLog(stime2,st2,"影像浏览器调用保存结果接口",responses.getCode(),jsonModel,responseBody1,"");
	    	            logger.debug("----------------新增平台检查结果------------"+resultResponse.getCode());
	    	            if("0000".equals(resultResponse.getCode())){
	    	            	jsonObj.put("resultCode", "0000");
	    	            	jsonObj.put("resultMsg", "AI接口上传数据成功");
	    	            }
	    	    	}else{
	    	    		//上传失败
//	    	    		jsonObj.put("resultCode",responses.getCode());
//	    	    		jsonObj.put("resultMsg", "AI接口上传数据失败");
	    	    	}
				}
        	}
        	else{
        		//说明平台已有AI结果
        		jsonObj.put("resultCode", "0000");
        		jsonObj.put("resultMsg", "AI结果"+response.getData().getResultMsg());
        	}
        }else{
        	//查询平台结果失败
        	jsonObj.put("resultCode", response.getCode());
        	jsonObj.put("resultMsg", "平台结果查询失败，请联系管理员");
        }
        return jsonObj;
    }
    
    /**
     * ai数据结果查询------pacs调用
     */
    public JSONObject aiDataResult(String studyinfoId,String orgId) throws Exception{
    	JSONObject jsonObj = new JSONObject();
    	String ai_selfUpdate_url = PropertiesUtils.getContextProperty("ai_selfUpdate_url");
    	String ai_repeatResult_url = PropertiesUtils.getContextProperty("ai_repeatResult_url");
    	String ai_selfQry_url = PropertiesUtils.getContextProperty("ai_selfQry_url");
    	String ai_partnerId = PropertiesUtils.getContextProperty("ai_partnerId");
    	String ai_token = PropertiesUtils.getContextProperty("ai_token");
    	Timestamp dateTime=new Timestamp(new Date().getTime());
    	DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String uuid = sdf.format(dateTime)+dateTime.getTime();
    	HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
    	QryResultRequest result = new QryResultRequest();
        result.setStudyId(studyinfoId);
        result.setOrgId(orgId);
        String json = JsonUtil.toJson(result);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ai_selfQry_url, entity, String.class);
        String responseBody = responseEntity.getBody();
        JSONObject jsonobject = JSONObject.fromObject(responseBody);
        
        long st=System.currentTimeMillis();
		Timestamp stime=new Timestamp(System.currentTimeMillis());
        QryResultResponse response = (QryResultResponse) JSONObject.toBean(jsonobject,QryResultResponse.class);
        this.addLog(stime,st,"PACS调用查询结果接口",response.getCode(),json,responseBody,"");
        //首先判断平台是否有结果数据
        if("0000".equals(response.getCode())){
        	if("1".equals(response.getData().getStatus())){
        		jsonObj.put("resultCode", "9995");
	        	jsonObj.put("resultMsg", "AI数据暂未上传，请上传后查看");
        	}else if("2".equals(response.getData().getStatus())){
        		DicomResultRequest request = new DicomResultRequest();
    			request.setStudyId(studyinfoId);
    			String requestBody = JsonUtil.toJson(request);
    			String aiResponse = getHttpResponse(ai_repeatResult_url,requestBody,ai_partnerId,ai_token,uuid);
    			JSONObject responseObj = JSONObject.fromObject(aiResponse);
    			JsonRootBean responses = (JsonRootBean) JSONObject.toBean(responseObj,JsonRootBean.class);
    			long st1=System.currentTimeMillis();
    			Timestamp stime1=new Timestamp(System.currentTimeMillis());
    	        this.addLog(stime1,st1,"PACS调用查询腾讯结果接口",String.valueOf(responses.getCode()),requestBody,aiResponse,"");
    			if(responses.getCode()==0){
    	    		//更新平台检查信息表
    	        	UpdateAiResultRequest model = new UpdateAiResultRequest();
    	        	List<MarkList> list = responses.getData().getMarkList();
    	    		model.setMarkList(list);
    	        	model.setStudyId(studyinfoId);
    	        	model.setOrgId(orgId);
    	        	model.setResult(String.valueOf(responses.getData().getResult()));
    	    		if("-3".equals(responses.getData().getResult())||"-2".equals(responses.getData().getResult())||"2".equals(responses.getData().getResult())||"3".equals(responses.getData().getResult())){
    	    			model.setStatus("3");
    	    		}else{
    	    			model.setStatus("2");
    	    		}
    	    		model.setCode(String.valueOf(responses.getCode()));
    	    		model.setMessage(responses.getMessage());
    	            String jsonModel = JsonUtil.toJson(model);
    	    		HttpEntity<String> entity1 = new HttpEntity<String>(jsonModel, headers);
    	            ResponseEntity<String> responseEntity2 = restTemplate.postForEntity(ai_selfUpdate_url, entity1, String.class);
    	            String responseBody2 = responseEntity2.getBody();
    	            JSONObject resultBody2 = JSONObject.fromObject(responseBody2);
    	            AIQryResultResponse resultResponse2 = (AIQryResultResponse) JSONObject.toBean(resultBody2,AIQryResultResponse.class);
    	            long st2=System.currentTimeMillis();
        			Timestamp stime2=new Timestamp(System.currentTimeMillis());
        	        this.addLog(stime2,st2,"PACS调用更新结果接口",resultResponse2.getCode(),jsonModel,responseBody2,"");
    	            if("0000".equals(resultResponse2.getCode())){
    	            	jsonObj.put("resultCode", "0000");
    	            	if(-3==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果连接引擎失败");
    	            	}else if(-2==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果处理失败");
    	            	}else if(-1==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","未处理");
    	            	}else if(0==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果正在处理");
    	            	}else if(1==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果没有状态");
    	            	}else if(2==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果正常，无肺结节");
    	            	}else if(3==responses.getData().getResult()){
    	            		jsonObj.put("resultMsg","AI结果高风险肺结节");
    	            	}
    	            }else{
    	            	jsonObj.put("resultCode", responses.getCode());
        	        	jsonObj.put("resultMsg", "平台结果更新失败，请联系管理员");
    	            }
    	    	}else{
    	    		//查询AI结果失败
    	    		jsonObj.put("resultCode", responses.getCode());
    	        	jsonObj.put("resultMsg", "查询AI结果失败，请联系管理员");
    	    	}
        	}else{
        		jsonObj.put("resultCode", response.getData().getResult());
	        	jsonObj.put("resultMsg", "查询AI结果"+response.getData().getResultMsg());
        	}
        }
    	return jsonObj;
    }
    
    
    /**
     * 将文件转成base64 字符串
     * @param path文件路径
     * @return  * 
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
//    	FTPSUtil ftpUtil = new FTPSUtil();
//    	ftpUtil.connect("",80,"","");
		//fileSize = ftpUtil.getFileSize(reportFileBean.getMediumPath()+"/"+reportFileBean.getFilePath());
		//String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
		//ftpUtil.;
       SmbFile smbfile=new SmbFile(path);
       SmbFileInputStream in = new SmbFileInputStream(smbfile);  
       byte[] buffer = new byte[(int) smbfile.length()];
       in.read(buffer);
       in.close();
       return Base64Encoder.encode(buffer);
//       File file = new File(path);
//	   FileInputStream inputFile = new FileInputStream(file);
//	   byte[] buffer = new byte[(int) file.length()];
//	   inputFile.read(buffer);
//	   inputFile.close();
//	   return Base64Encoder.encode(buffer);
	}
    public static void main(String[] args) {
    	try {
    		Timestamp dateTime=new Timestamp(new Date().getTime());
    		String timestamp = Long.toString(new Date().getTime());
    		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        	String uuid = sdf.format(dateTime)+dateTime.getTime();
    		String aaa = sha256("1a7gigdnyM5SmCS0LfawYSrwf6r7NzJ8","27228889"+timestamp);
    		System.out.println(uuid);
    		System.out.println(timestamp);
			System.out.println(aaa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private final static char[] hexArray = "0123456789abcdef".toCharArray();

    public String getHttpResponse(String httpUrl,String requestBody,String partnerId,String token,String request_id) throws Exception{
    	String timestamp = Long.toString(new Date().getTime());
    	String sign = sha256(token,partnerId+timestamp);
    	HttpHeaders headers = new HttpHeaders();
    	MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    	headers.setContentType(type);
    	headers.add("partnerId",partnerId);
    	headers.add("god-portal-signature",sign);
        headers.add("god-portal-timestamp",timestamp);
        headers.add("god-portal-request-id",request_id);
        String url = httpUrl;
        
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        logger.error("entity=="+entity);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }
    
    public static String sha256(String token, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(token.getBytes("UTF-8"),
				"HmacSHA256");
		sha256_HMAC.init(secret_key);

		return bytesToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}
    
    private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
    
    //根据检查ID查报告信息
    public QryReportBrowseBean getReportContrast(String studyinfoId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        QryReportBrowseBean[] ruleBean = QryReportBrowseEngine.getBeans(condition.toString(), null);

        if (ruleBean.length > 0) {
            return ruleBean[0];
        } else {
            return new QryReportBrowseBean();
        }
    }

    @Override
    public List<Map> getReportSelect(String studyinfoId) throws Exception {
        Map map = new HashMap();
        map.put("studyinfoId", studyinfoId);
        AisStudyReportHBean[] beans = AisStudyReportHEngine.getBeans(" studyinfo_id = :studyinfoId ORDER BY report_history_id ASC", map);
        List<Map> list = new ArrayList();
        for (AisStudyReportHBean studyhBean : beans) {
            list.add(ServiceUtil.transerBeanToMap(studyhBean));
        }
        return list;
    }

    //
    public QryReportHBean getReportH(String reportHistoryId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(reportHistoryId)) {
            condition.append(" AND REPORT_HISTORY_ID = " + reportHistoryId);
        }
        QryReportHBean[] ruleBean = QryReportHEngine.getBeans(condition.toString(), null);

        if (ruleBean.length > 0) {
            return ruleBean[0];
        } else {
            return new QryReportHBean();
        }
    }

    //用户短语查询
    public AiscUserPhraseBean[] getPhrase(String operatorId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(operatorId)) {
            condition.append(" AND OPERATOR_ID = " + operatorId);
        }
        condition.append(" ORDER BY PHRASE_ID DESC ");
        AiscUserPhraseBean[] phraseBeans = AiscUserPhraseEngine.getBeans(condition.toString(), null);

        return phraseBeans;
    }

    //添加短语
    public void addPhrase(String operatorId, String pInfo) throws Exception {
        AiscUserPhraseBean bean = new AiscUserPhraseBean();
        bean.setPhraseId(ServiceUtil.getSequence("SEQPHRASEID"));
        bean.setOperatorId(Long.parseLong(operatorId));
        bean.setPhraseContent(pInfo);
        AiscUserPhraseEngine.save(bean);
    }

    //删除短语
    public void delPhrase(String pId) throws Exception {
        AiscUserPhraseBean phraseBeans = AiscUserPhraseEngine.getBean(Long.parseLong(pId));
        phraseBeans.setStsToOld();
        phraseBeans.delete();
        AiscUserPhraseEngine.save(phraseBeans);
    }

    //修改短语
    public void updatePhrase(String pId, String pInfo) throws Exception {
        AiscUserPhraseBean phraseBeans = AiscUserPhraseEngine.getBean(Long.parseLong(pId));
        AiscUserPhraseBean oldBean = new AiscUserPhraseBean();
        phraseBeans.setPhraseContent(pInfo);
        oldBean.setStsToOld();
        DataContainerFactory.copyNoClearData(phraseBeans, oldBean);

        AiscUserPhraseEngine.save(oldBean);
    }

    //相关检查查询
    public QryRegInfoBean[] getRelCheck(String patientGlobalid, String studyinfoId,String patientInpatientId,String patientIdnumber) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(patientGlobalid)) {
            condition.append(" and (PATIENT_GLOBALID = " + patientGlobalid);
            if (isNotBlank(studyinfoId)) {
            	condition.append(" AND STUDYINFO_ID != " + studyinfoId);
            }
            condition.append(" AND STUDY_HAVEREPORT = 1 )");
        }
        if (isNotBlank(patientIdnumber)) {
            condition.append(" or patient_idnumber = '" + patientIdnumber+"'");
        }
        if (isNotBlank(patientInpatientId)) {
            condition.append(" or patient_inpatientid = '" + patientInpatientId+"'");
        }
        condition.append(" ORDER BY STUDYINFO_ID DESC ");
        QryRegInfoBean[] regBeans = QryRegInfoEngine.getBeans(condition.toString(), null);

        return regBeans;
    }

    /**
     * 取得数据库时间，解析时间
     */
    public static String getDateday(Timestamp t) {
        String str = t.toString();
        str = str.replace("-", "");
        String date = str.substring(6, 8);
        return date;
    }

    //获取报告存在文件地址
    public QryReportFileBean getReportPath(String reportId, String status) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(reportId)) {
            condition.append(" AND REPORT_ID = " + reportId);
        }
        if (isNotBlank(status)) {
            condition.append(" AND status = '" + status + "' ");
        }
        condition.append(" AND medium_enabled = 1 ");
        condition.append(" AND server_enabled = 1 ");
        condition.append(" ORDER BY FILE_ID DESC ");
        QryReportFileBean[] reportFileBean = QryReportFileEngine.getBeans(condition.toString(), null);

        if (reportFileBean.length > 0) {
            return reportFileBean[0];
        } else {
            return null;
        }
    }

    //获取服务器图片存储地址
    public QryReportFileBean[] getReportImgupload(String studyinfoId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND studyinfo_id = " + studyinfoId);
        }
        condition.append(" AND medium_enabled = 1 ");
        condition.append(" AND server_enabled = 1 ");
        condition.append(" ORDER BY FILE_ID DESC ");
        QryReportFileBean[] reportFileBean = QryReportFileEngine.getBeans(condition.toString(), null);
        if (reportFileBean.length > 0) {
            return reportFileBean;
        }
        return null;
    }

    @Override
    public boolean deleteNodes(long id) throws Exception {
        AiscTemplatecatDirBean bean = AiscTemplatecatDirEngine.getBean(id);
        bean.setStsToOld();
        bean.delete();
        Statement stmt = null;
        try {
            AiscTemplatecatDirEngine.save(bean);
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "delete from aisc_tpldir2loc where templatedir_id=" + id;
            stmt.executeUpdate(sql);
            String sql1 = "delete from aisc_templatecontent where templatedir_id=" + id;
            stmt.executeUpdate(sql1);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }

    @Override
    public String saveReportFileUpload(MultipartFile file, String filePath, String fileName, String reportId,User user,String patientId,String patientName) throws Exception{
        //判断已上传的图片不再上传
        Timestamp time= new Timestamp(System.currentTimeMillis());//获取系统当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AisReportUploadBean oldBean = getAisReportUpload(reportId, fileName,patientId,patientName);
        Date date = new Date();
        if (oldBean == null) {
            //将本地文件上传至服务器
            File targetFile = new File(filePath,fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            try {
                //记录文件地址到库表中
                AisReportUploadBean fileBean = new AisReportUploadBean();
                long id = ServiceUtil.getSequence("SEQ_AIS_REPORT_UPLOAD");
                fileBean.setId(id);
                fileBean.setFileName(fileName);
                fileBean.setOperatorCode(user.getOperatorCode());
                fileBean.setOperatorName(user.getOperatorName());
                fileBean.setReportId(Long.parseLong(reportId));
                fileBean.setUploadtime(Timestamp.valueOf(sdf.format(time)));
                fileBean.setPatientName(patientName);
                fileBean.setPatientId(patientId);
                AisReportUploadEngine.save(fileBean);
                return "0";

            } catch (Exception e) {
                logger.error("上传报告文件异常,请稍后再试：" + e.getMessage());
            }
        }else{
            return "1";
        }
        return "0";
    }
    public AisReportUploadBean [] queryFileUpload(String reportId,String patientId,String patientName) throws Exception{
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(reportId)) {
            condition.append(" AND REPORT_ID = " + reportId);
        }
        if (isNotBlank(patientId)) {
            condition.append(" AND PATIENT_ID = '" + patientId + "'");
        }
        if (isNotBlank(patientName)) {
            condition.append(" AND PATIENT_NAME = '" + patientName + "'");
        }
        AisReportUploadBean[] bean = AisReportUploadEngine.getBeans(condition.toString(), null);
        return bean;
    }
    private AisReportUploadBean getAisReportUpload(String reportId, String fileName,String patientId,String patientName) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(reportId)) {
            condition.append(" AND REPORT_ID = " + reportId);
        }
        if (isNotBlank(fileName)) {
            condition.append(" AND FILE_NAME = '" + fileName + "'");
        }
        if (isNotBlank(patientId)) {
            condition.append(" AND PATIENT_ID = '" + patientId + "'");
        }
        if (isNotBlank(patientName)) {
            condition.append(" AND PATIENT_NAME = '" + patientName + "'");
        }
        AisReportUploadBean[] bean = AisReportUploadEngine.getBeans(condition.toString(), null);

        if (bean.length > 0) {
            return bean[0];
        }
        return null;
    }

    @Override
    public boolean deletebingliNodes(long id) throws Exception {
        AisKnowledgebaseBean bean = AisKnowledgebaseEngine.getBean(id);
        bean.setStsToOld();
        bean.delete();
        AisKnowledgebaseEngine.save(bean);
        return true;
    }

    @Override
    public boolean editNodes(long id, String name) throws Exception {
        AiscTemplatecatDirBean bean = AiscTemplatecatDirEngine.getBean(id);
        bean.setStsToOld();
        bean.setTemplatedirDesc(name);
        try {
            AiscTemplatecatDirEngine.save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean editbingliNodes(long id, String name) throws Exception {
        AisKnowledgebaseBean bean = AisKnowledgebaseEngine.getBean(id);
        bean.setStsToOld();
        bean.setCasegroupdesc(name);
        try {
        	AisKnowledgebaseEngine.save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long getNodeIndex() throws Exception {
        return ServiceUtil.getSequence("SEQNODEINDEXID");
    }

    @Override
    public boolean getIshaveNode(long id, long pId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and templatedir_pdirid=" + pId + " and templatecat_id=" + id);
        int beanCount = AiscTemplatecatDirEngine.getBeansCount(condition.toString(), null);
        if (beanCount > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveNode(long id, long pId, String name, long locId, long orgId, long isDirectory, long operator_id, long templatedir_flag, String modalityId) throws Exception {
        Statement stmt = null;
        String sql1 = "";
        try{
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = "insert into AISC_TemplateDIR values (" + id + ", '" + name + "',(select max(templatedir_order)+1 from aisc_templatedir), 1, " + pId + "," + isDirectory + "," + templatedir_flag + ",'" + orgId + "','" + modalityId + "')";
	        int count = stmt.executeUpdate(sql);
	        if (operator_id != 0) {
	            sql1 = "insert into aisc_tpldir2loc (TPLCAT2LOC_ID,MODALITY_ID,LOC_ID,TEMPLATEDIR_ID,ORG_ID,OPERATOR_ID) values(seqtpcat2locid.nextval,"+Integer.parseInt(modalityId)+"," + locId + "," + id + "," + orgId + "," + operator_id + ")";
	        } else {
	            sql1 = "insert into aisc_tpldir2loc (TPLCAT2LOC_ID,MODALITY_ID,LOC_ID,TEMPLATEDIR_ID,ORG_ID) values(seqtpcat2locid.nextval,"+Integer.parseInt(modalityId)+"," + locId + "," + id + "," + orgId + ")";
	        }
	        int count1 = stmt.executeUpdate(sql1);
	        if (count + count1 == 2) {
	            return true;
	        }
        }catch (Exception e) {
        	
        }finally{
	        DBUtil.release(null, stmt, null);
	        ServiceManager.getSession().getConnection().close();
        }
        return false;
    }

    @Override
    public int isHaveDir(String dirName, long templatedir_flag, String orgId, long id) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   select count(*) COUNT from aisc_templatedir where templatedir_desc='" + dirName + "' and templatedir_flag=" + templatedir_flag + " and org_id='" + orgId + "'";
            if (id != 0) {
                sql += " and  templatedir_pdirid='" + id + "' ";
            }
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("COUNT");
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return 0;
    }

    //父目录判断&子目录判断公用
    @Override
    public long getNodeId(String dirName, long templatedir_flag, String orgId) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   select distinct TEMPLATEDIR_ID from aisc_templatedir where templatedir_desc='" + dirName + "' and templatedir_flag=" + templatedir_flag + " and org_id='" + orgId + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getLong("TEMPLATEDIR_ID");
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return 0;
    }

    @Override
    public ResultDTO queryPageList(TemplateContent model, ResultDTO resultDTO) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rsCount = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "select * from (select B.*,rownum as row_index from ( select (select templatedir_desc from aisc_templatedir where templatedir_id=a.templatedir_id) TEMPLATEDIR_DESC,a.templatecontent_id,a.templatedir_id,a.template_name," +
                    "a.template_method,a.template_exam,a.template_result,a.template_fq from Aisc_Templatecontent a where 1=1 ";
            String sqlParam = "";
            if (isNotBlank(String.valueOf(model.getTemplateName()))) {
                sqlParam += " and a.template_name like '%" + model.getTemplateName() + "%' ";
            }
            if (isNotBlank(String.valueOf(model.getTemplateMethod()))) {
                sqlParam += " and a.template_method like '%" + model.getTemplateMethod() + "%' ";
            }
            if (isNotBlank(String.valueOf(model.getTemplatedirId())) && model.getTemplatedirId() != null) {
                sqlParam += " and a.templatedir_id = " + model.getTemplatedirId();
            }
            int number = resultDTO.getLimit() * resultDTO.getPage();
            int rowindex = 1 + (number - resultDTO.getLimit());
            String sqlOrder = " ORDER BY a.templatecontent_id asc ) B where rownum<=" + number + ") where  row_index >=" + rowindex + " and row_index <= " + number + " ";
            rs = stmt.executeQuery(sql + sqlParam + sqlOrder);

            List<TemplateContent> list = new ArrayList();
            TemplateContent tempc = null;
            while (rs.next()) {
                tempc = new TemplateContent();
                tempc.setTemplatecontentId(rs.getLong("TEMPLATECONTENT_ID"));
                tempc.setTemplatedirId(rs.getLong("TEMPLATEDIR_ID"));
                tempc.setTemplateFq(rs.getLong("TEMPLATE_FQ"));
                tempc.setTemplatedirDesc(rs.getString("TEMPLATEDIR_DESC") == null ? "" : rs.getString("TEMPLATEDIR_DESC"));
                tempc.setTemplateName(rs.getString("TEMPLATE_NAME") == null ? "" : rs.getString("TEMPLATE_NAME"));
                tempc.setTemplateMethod(rs.getString("TEMPLATE_METHOD") == null ? "" : rs.getString("TEMPLATE_METHOD"));
                Clob templateExam = rs.getClob("TEMPLATE_EXAM");
                String format = "";
                if (templateExam != null) {
                    format = templateExam.getSubString(1, (int) templateExam.length());
                }
                tempc.setTemplateExam(format);
                Clob templateResult = rs.getClob("TEMPLATE_RESULT");
                String format1 = "";
                if (templateResult != null) {
                    format1 = templateResult.getSubString(1, (int) templateResult.length());
                }
                tempc.setTemplateResult(format1);

                list.add(tempc);
            }
            String sqlCount = "select count(*) COUNT from Aisc_Templatecontent a where 1=1 " + sqlParam;
            int total = 0;
            rsCount = stmt.executeQuery(sqlCount);
            if (rsCount.next()) {
                total = rsCount.getInt("COUNT");
            }

            TemplateContent[] tempclist = new TemplateContent[list.size()];
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    tempclist[i] = list.get(i);
                }
            }
            Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
            resultDTO.setRows(BeanUtils.beanToList(tempclist, TemplateContent.class, dicts));
            resultDTO.setRecords(total);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            DBUtil.release(rsCount, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return resultDTO;
    }

    /**
     * 模板详情
     */
    @Override
    public TemplateContent getAiscTemplateDetail(long dirId) throws Exception {
        Statement stmt = null;
        TemplateContent bean = new TemplateContent();
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "select * from aisc_templatecontent where templatedir_id=" + dirId;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setTemplatecontentId(rs.getLong("TEMPLATECONTENT_ID"));
                bean.setTemplatedirId(rs.getLong("TEMPLATEDIR_ID"));
                bean.setIspositive(rs.getLong("ISPOSITIVE"));
                bean.setTemplateName(rs.getString("TEMPLATE_NAME") == null ? "" : rs.getString("TEMPLATE_NAME"));
                bean.setTemplateMethod(rs.getString("TEMPLATE_METHOD") == null ? "" : rs.getString("TEMPLATE_METHOD"));
                Clob reportFormat = rs.getClob("TEMPLATE_RESULT");

                String format = "";
                if (reportFormat != null) {
                    format = reportFormat.getSubString(1, (int) reportFormat.length());
                }
                bean.setTemplateResult(format);
                Clob reportFormat1 = rs.getClob("TEMPLATE_EXAM");
                String format1 = "";
                if (reportFormat1 != null) {
                    format1 = reportFormat1.getSubString(1, (int) reportFormat1.length());
                }
                bean.setTemplateExam(format1);
                bean.setTemplateFq(rs.getLong("TEMPLATE_FQ"));
                return bean;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return null;
    }
    
    @Override
    public void updateKnowledgebase(TemplateContent bean) throws Exception {
    	Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sqlUp = "update AIS_KNOWLEDGEBASE set template_exam=' ',template_result=' ' where knowledgebaseid=" + bean.getKnowledgebaseid();
            stmt.executeUpdate(sqlUp);
            //clob字段更新
            PreparedStatement preStmt = ServiceManager.getSession().getConnection().prepareStatement("select * from AIS_KNOWLEDGEBASE where knowledgebaseid=" + bean.getKnowledgebaseid() + " for update");
            rs = preStmt.executeQuery();

            if (rs.next()) {
                CLOB clob1 = (CLOB) rs.getClob("TEMPLATE_EXAM");
                Writer outStream = clob1.getCharacterOutputStream();
                char[] c = bean.getTemplateExam().toCharArray();
                outStream.write(c, 0, c.length);
                outStream.flush();
                outStream.close();
                CLOB clob2 = (CLOB) rs.getClob("TEMPLATE_RESULT");
                Writer outStream1 = clob2.getCharacterOutputStream();
                char[] c1 = bean.getTemplateResult().toCharArray();
                outStream1.write(c1, 0, c1.length);
                outStream1.flush();
                outStream1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }

    @Override
    public void fileDelete(String fileName, String patientName, String patientId, String reportId) throws Exception {
        StringBuffer sql = new StringBuffer(" DELETE  FROM  AIS_REPORT_UPLOAD WHERE 1=1");
        sql.append(" AND REPORT_ID = " + reportId);
        sql.append(" AND FILE_NAME = '" + fileName + "'");
        sql.append(" AND PATIENT_ID = '" + patientId + "'");
        sql.append(" AND PATIENT_NAME = '" + patientName + "'");
        ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), sql.toString(), null);
    }

    @Override
    public void saveTemplateDetail(TemplateContent bean) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        if (bean.getTemplatecontentId() != null && bean.getTemplatecontentId() != 0) {
            try {
                stmt = ServiceManager.getSession().getConnection().createStatement();
                String sqlUp = "update AISC_TEMPLATECONTENT set template_exam=' ',template_result=' ' where templatecontent_id=" + bean.getTemplatecontentId() + "";
                stmt.executeUpdate(sqlUp);
                //clob字段更新
                PreparedStatement preStmt = ServiceManager.getSession().getConnection().prepareStatement("select * from AISC_TEMPLATECONTENT where templatecontent_id=" + bean.getTemplatecontentId() + " for update");
                rs = preStmt.executeQuery();

                if (rs.next()) {
                    CLOB clob1 = (CLOB) rs.getClob("TEMPLATE_EXAM");
                    Writer outStream = clob1.getCharacterOutputStream();
                    char[] c = bean.getTemplateExam().toCharArray();
                    outStream.write(c, 0, c.length);
                    outStream.flush();
                    outStream.close();
                    CLOB clob2 = (CLOB) rs.getClob("TEMPLATE_RESULT");
                    Writer outStream1 = clob2.getCharacterOutputStream();
                    char[] c1 = bean.getTemplateResult().toCharArray();
                    outStream1.write(c1, 0, c1.length);
                    outStream1.flush();
                    outStream1.close();
                }
                String sql = "update AISC_TEMPLATECONTENT set template_name='" + bean.getTemplateName() + "',template_method='" + bean.getTemplateMethod() + "',ispositive=" + bean.getIspositive() + "" +
                        " where templatecontent_id=" + bean.getTemplatecontentId() + "";
                preStmt.executeUpdate(sql);

                String dirsql = "update aisc_templatedir set templatedir_desc='" + bean.getTemplateName() + "' where templatedir_id=" + bean.getTemplatedirId();
                stmt.executeUpdate(dirsql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.release(rs, stmt, null);
                ServiceManager.getSession().getConnection().close();
            }
        } else {
            try {
                long formatid = ServiceUtil.getSequence("SEQTEMPLATECONTENTID");
                stmt = ServiceManager.getSession().getConnection().createStatement();
                String sql = "insert into AISC_TEMPLATECONTENT values (" + formatid + "," + bean.getTemplatedirId() + ",'" + bean.getTemplateName() + "','" + bean.getTemplateMethod() + "',' ',' ',0," + bean.getIspositive() + ")";
                stmt.executeUpdate(sql);
                String dirsql = "update aisc_templatedir set templatedir_desc='" + bean.getTemplateName() + "' where templatedir_id=" + bean.getTemplatedirId();
                stmt.executeUpdate(dirsql);
                PreparedStatement presta = ServiceManager.getSession().getConnection().prepareStatement("select * from AISC_TEMPLATECONTENT where templatecontent_id=" + formatid + " for update");
                rs = presta.executeQuery();

                if (rs.next()) {
                    CLOB clob1 = (CLOB) rs.getClob("TEMPLATE_EXAM");
                    Writer outStream = clob1.getCharacterOutputStream();
                    char[] c = bean.getTemplateExam().toCharArray();
                    outStream.write(c, 0, c.length);
                    outStream.flush();
                    outStream.close();
                    CLOB clob2 = (CLOB) rs.getClob("TEMPLATE_RESULT");
                    Writer outStream1 = clob2.getCharacterOutputStream();
                    char[] c1 = bean.getTemplateResult().toCharArray();
                    outStream1.write(c1, 0, c1.length);
                    outStream1.flush();
                    outStream1.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.release(rs, stmt, null);
                ServiceManager.getSession().getConnection().close();
            }
        }
    }

    @Override
    public boolean deleteTemplate(long id) throws Exception {
        Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "delete from aisc_templatecontent where templatedir_id=" + id;
            int count = stmt.executeUpdate(sql);
            if (count == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return false;
    }

    //更新打印状态
    public void setReportPrintInfo(String reportId, User user) throws Exception {
        Timestamp dateTime = new Timestamp(new Date().getTime());
        AisStudyReportBean reportBean = AisStudyReportEngine.getBean(Long.parseLong(reportId));

        AisStudyReportBean oldBean = new AisStudyReportBean();
        oldBean.setReportId(Long.parseLong(reportId));
        oldBean.setStsToOld();

        oldBean.setReportIsprinted(1);
        oldBean.setReportPrintdatetime(dateTime);
        oldBean.setReportPrintcareprovid(user.getCareProvId());
        DataContainerFactory.copyNoClearData(reportBean, oldBean);
        AisStudyReportEngine.save(oldBean);
    }

    //根据科室标识，查询存储服务介质
    public QryServerMediumBean[] getServerMedium(String locId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(locId)) {
            condition.append(" AND LOC_ID = " + locId);
        }
        condition.append(" AND SERVER_ENABLED = 1");  //状态有效
        condition.append(" AND MEDIUM_ENABLED = 1");  //状态有效
        //condition.append(" AND Medium_IsFull = 0");   //未满
        //condition.append(" AND Medium_IsOnline = 1"); //在线

        condition.append(" ORDER BY MEDIUM_ID ASC");
        QryServerMediumBean[] serverMediums = QryServerMediumEngine.getBeans(condition.toString(), null);

        return serverMediums;
//	    if(serverMediums.length>0){
//	    	return serverMediums[0];
//	    }else{
//	    	return new QryServerMediumBean();
//	    }

    }

    //写文件到本地路径
    private static void WriteFile(String locPath, String fileName, String strs) throws Exception {
        FileUtil.makeDirs(locPath);
        FileWriter fileWritter = new FileWriter(locPath + "/" + fileName + ".html", false);
        logger.info("PATH:" + locPath + "/" + fileName + "================");
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(strs);
        bufferWritter.close();
    }

    //上传文件到FTP服务器
    private static void Upload(FTPSUtil ftpUtil, String directory, String path, String filename) throws Exception {
        if (!"".equals(directory)) {
            ftpUtil.makeDirectoryNoEnter(directory);
        }
        ftpUtil.upload(directory, path + "/" + filename + ".html", filename + ".tmp");
        ftpUtil.renameWithOutDir(directory + "/" + filename + ".tmp", directory + "/" + filename + ".html");
    }

    //删除本地路径下的所有文件
    public static void deleteFile(File oldPath) {
        if (oldPath.isDirectory()) {
            File[] files = oldPath.listFiles();
            for (File file : files) {
                deleteFile(file);
            }
        } else {
            oldPath.delete();
        }
    }

//    public static void main(String[] args) {
//        System.out.println(ToCH(99));
//        ;
//    	FTPSUtil ftpUtil = new FTPSUtil();
//		 try {
//
//			ftpUtil.connect("10.63.90.226", 21, "ftp123", "Ftp_123*456AB");
//		    ftpUtil.upload("", "C:/PACS_FILE/aaa.txt");

//			ftpUtil.connect("10.63.90.226", 21, "ftp123", "Ftp_123*456AB");
//			FTPFile[] ff=ftpUtil.listFiles("");
//			for(int i=0;i<=ff.length;i++){
//				System.out.println(">>>>>>"+ff[i].getName());
//			}
//			//ftpUtil.connect("192.168.1.108", 21, "ruanbh", "123");
//			//ftpUtil.upload("", "C:/PACS_FILE/aaa.txt");
//
//			 FTPSClient ftpClient = new FTPSClient(false);
//		      // Connect to host
//		      ftpClient.connect("10.63.90.226", 21);
//		      int reply = ftpClient.getReplyCode();
//		      if (FTPReply.isPositiveCompletion(reply)) {
//
//		        // Login
//		        if (ftpClient.login("ftp123", "Ftp_123*456AB")) {
//		        	// Set protection buffer size
//		            ftpClient.execPBSZ(0);
//		            // Set data channel protection to private
//		            ftpClient.execPROT("P");
//		            // Enter local passive mode
//		            ftpClient.enterLocalPassiveMode();
//		            FTPFile[] fff=ftpClient.listFiles("/");
//					for(int i=0;i<=fff.length;i++){
//						System.out.println(">>>>>>"+fff[i].getName());
//					}
//		        }
//		      }
//

//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

    @Override
    public void consultStart(String studyinfoId, String getOperatorId,
                             String reportId) throws Exception {
        //1. 查检查记录表 取医嘱类型  更新医嘱状态为 会诊开始
        Timestamp dateTime = new Timestamp(new Date().getTime());
        AisStudyInfoBean oldStudyinfo = sv.getStudyInfoBean(studyinfoId);
        if (null != oldStudyinfo) {
            oldStudyinfo.setStudystatusCode("Consulting");//会诊开始
            oldStudyinfo.setStudyStarttime(dateTime);
            AisStudyInfoEngine.save(oldStudyinfo);
        }

    }

    public AisReportFilesBean[] getfileBeans(long reportId, String status) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (reportId != 0) {
            condition.append(" AND report_Id = " + reportId);
        }
        if (isNotBlank(status)) {
            condition.append(" AND status = '" + status + "' ");
        }

        AisReportFilesBean[] reportFile = AisReportFilesEngine.getBeans(condition.toString(), null);
        if (reportFile.length > 0) {
            return reportFile;
        } else {
            return null;
        }
    }

    public int getMaxReportNum(long reportId, long studyinfoId, String status) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "select max(report_num) report_num from ais_studyreport_h where report_id=" + reportId + " and studyinfo_id=" + studyinfoId + " and status='VERIFY' ";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("report_num");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return count;
    }

    public AisStudyReportHBean[] getReportHisBeans(long reportId, long studyinfoId, String status) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (reportId != 0) {
            condition.append(" AND report_Id = " + reportId);
        }
        if (studyinfoId != 0) {
            condition.append(" AND studyinfo_Id =  " + studyinfoId);
        }
        if (isNotBlank(status)) {
            condition.append(" AND status = '" + status + "' ");
        }

        AisStudyReportHBean[] hisBean = AisStudyReportHEngine.getBeans(condition.toString(), null);
        if (hisBean != null && hisBean.length > 0) {
            return hisBean;
        } else {
            return null;
        }
    }

    public QryReportBrowseBean getReportByAccnumber(String studyAccnumber, String patientId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyAccnumber)) {
            condition.append(" AND study_accnumber = '" + studyAccnumber + "'");
        }
        if (isNotBlank(studyAccnumber)) {
            condition.append(" AND patient_Id = '" + patientId + "'");
        }
        QryReportBrowseBean[] regInfoBeans = QryReportBrowseEngine.getBeans(condition.toString(), null);
        if (regInfoBeans.length > 0) {
            return regInfoBeans[0];
        } else {
            return new QryReportBrowseBean();
        }
    }
    
    public AisStudyInfoBean getStudyInfoByAccnumber(String studyAccnumber) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyAccnumber)) {
            condition.append(" AND study_accnumber = '" + studyAccnumber + "'");
        }
        AisStudyInfoBean[] studyBean = AisStudyInfoEngine.getBeans(condition.toString(), null);
        if (studyBean.length > 0) {
            return studyBean[0];
        } else {
            return null;
        }
    }

    public QryReportBrowseBean getReportByInoutPatinetId(String orgId, String patientType, String outInPatientId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" AND org_id = '" + orgId + "'");
        }
        if (isNotBlank(patientType)) {
            condition.append(" AND patienttype_code = '" + patientType + "'");
            if (isNotBlank(outInPatientId)) {
                //住院
                if ("INP".equals(patientType)) {
                    condition.append(" and patient_inpatientid = '" + outInPatientId + "' or his_zydjxh='"+outInPatientId+"' ");
                }
                if ("OP".equals(patientType)) {
                    condition.append(" and patient_outpatientid = '" + outInPatientId + "' or his_zydjxh='"+outInPatientId+"'");
                }
            }
        }
        QryReportBrowseBean[] regInfoBeans = QryReportBrowseEngine.getBeans(condition.toString(), null);
        if (regInfoBeans.length > 0) {
            return regInfoBeans[0];
        } else {
            return new QryReportBrowseBean();
        }
    }

    public QryReportBrowseBean getReportByOther(String name, String idNo, String birthDate) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(name)) {
            condition.append(" AND patient_name = '" + name + "'");
        }
        if (isNotBlank(birthDate)) {
            condition.append(" AND to_char(patient_dob,'yyyy-mm-dd') = '" + birthDate + "'");
        }
        if (isNotBlank(idNo)) {
            condition.append(" or patient_idnumber = '" + idNo + "'");
        }

        QryReportBrowseBean[] regInfoBeans = QryReportBrowseEngine.getBeans(condition.toString(), null);
        if (regInfoBeans.length > 0) {
            return regInfoBeans[0];
        } else {
            return new QryReportBrowseBean();
        }
    }

    public ResultDTO queryReportList(QryStudyInfoListModel model, ResultDTO resultDTO, String sidx, String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(model.getPatientInpatientid()) && !"0".equals(model.getPatientInpatientid())) {
            condition.append(" and patient_inpatientid='" + model.getPatientInpatientid() + "'");
        } else if (isNotBlank(model.getPatientOutpatientid()) && !"0".equals(model.getPatientOutpatientid())) {
            condition.append(" and patient_outpatientid='" + model.getPatientOutpatientid() + "'");
        } else {
            condition.append(" and patient_id='" + model.getPatientId() + "'");
        }
        condition.append(" and org_id='" + model.getOrgId() + "'");
        int total = QryReportBrowseListEngine.getBeansCount(condition.toString(), null);
        QryReportBrowseListBean[] beans = null;

        if (total > 0) {
            //排序处理
            if (!"".equals(sidx)) {
                condition.append(" ORDER BY " + sidx + " " + sord + " ");
            } else {
                condition.append(" ORDER BY StudyInfo_ID desc");
            }

            beans = QryReportBrowseListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false);
            for (QryReportBrowseListBean bean : beans) {
                //有报告才显示报告时间及报告人,打印
                if (bean.getStudyHavereport() == 1) {
                    StringBuffer condition2 = new StringBuffer();
                    condition2.append(" studyinfo_id = '" + bean.getStudyinfoId() + "'");
                    condition2.append(" and rownum = 1 order by report_id desc ");
                    AisStudyReportBean[] reportBeans = AisStudyReportEngine.getBeans(condition2.toString(), null);
                    if (reportBeans.length > 0) {
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        bean.setReportDatetime(reportBeans[0].getReportDatetime() == null ? "" : sdf.format(reportBeans[0].getReportDatetime()));
                        bean.setReportDoctorname(reportBeans[0].getReportDoctorid() == null ? "" : getCareProvInfo(reportBeans[0].getReportDoctorid()));
                        bean.setReportVerifydoctorname(reportBeans[0].getReportVerifydoctorid() == null ? "" : getCareProvInfo(reportBeans[0].getReportVerifydoctorid()));
                        bean.setReportFinaldoctorname(reportBeans[0].getReportFinaldoctorid() == null ? "" : getCareProvInfo(reportBeans[0].getReportFinaldoctorid()));
                        bean.setReportVerifytime(reportBeans[0].getReportVerifydatetime() == null ? "" : sdf.format(reportBeans[0].getReportVerifydatetime()));
                    }
                }
            }
        }
        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans, QryStudyInfoListModel.class, dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    public static String ToCH(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += GetCH(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (GetCH(intInput / 10) + "十");
            sd += ToCH(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (GetCH(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += ToCH(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (GetCH(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += ToCH(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (GetCH(intInput / 10000) + "萬");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += ToCH(intInput % 10000);
        }

        return sd;
    }

    private static String GetCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

    @Override
    public List<AisKnowledgebaseModel> getAisKnowledgebaseKeyDesc(String dataSore) throws Exception {
    	Statement stmt = null;
        ResultSet rs = null;
        List<AisKnowledgebaseModel> list = new ArrayList();
        try {
        	stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = " select count(*) COUNT from AIS_KNOWLEDGEBASE where CASEGROUPDESC='"+dataSore+"'";
            int total = 0;
            ResultSet rsCount = stmt.executeQuery(sql);
            if (rsCount.next()) {
                total = rsCount.getInt("COUNT");
            }
            String sql1 = " select * from AIS_KNOWLEDGEBASE where CASEGROUPDESC='"+dataSore+"'";
            if (total > 20) {
                sql1 += " and rownum<=20 ";
            }

            rs = stmt.executeQuery(sql1);
            AisKnowledgebaseModel aisKnowledgebaseModel = null;
            while (rs.next()) {
                aisKnowledgebaseModel = new AisKnowledgebaseModel();
                aisKnowledgebaseModel.setKeydesc(rs.getString("KEYDESC"));
                list.add(aisKnowledgebaseModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return list;
    }
    @Override
    public List<AisKnowledgebaseModel> getAisKnowledgebase(String operatorId) throws Exception {
    	Statement stmt = null;
        ResultSet rs = null;
        List<AisKnowledgebaseModel> list = new ArrayList();
        try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = " SELECT COUNT(DISTINCT CASEGROUPDESC) COUNT FROM AIS_KNOWLEDGEBASE where COLLECTOR_CODE='"+operatorId+"'";
	        int total = 0;
	        ResultSet rsCount = stmt.executeQuery(sql);
	        if (rsCount.next()) {
	            total = rsCount.getInt("COUNT");
	        }
	        String sql1 = "select DISTINCT CASEGROUPDESC from AIS_KNOWLEDGEBASE where COLLECTOR_CODE='"+operatorId+"'";
	        if (total > 20) {
	            sql1 += " and rownum<=20 ";
	        }
	
	        rs = stmt.executeQuery(sql1);
	        AisKnowledgebaseModel aisKnowledgebaseModel = null;
	        while (rs.next()) {
	            aisKnowledgebaseModel = new AisKnowledgebaseModel();
	            aisKnowledgebaseModel.setCasegroupdesc(rs.getString("CASEGROUPDESC"));
	            list.add(aisKnowledgebaseModel);
	        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return list;
    }

    public void saveAisKnowledgebase(String reportId, String operatorId,String keydesc) throws Exception {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        AisKnowledgebaseBean aisKnowledgebaseBean = new AisKnowledgebaseBean();
        long id = ServiceUtil.getSequence("SEQ_AIS_KNOWLEDGEBASE");
        aisKnowledgebaseBean.setKnowledgebaseid(id);
        aisKnowledgebaseBean.setKeydesc(keydesc);
        aisKnowledgebaseBean.setCasegroupdesc(keydesc);
        aisKnowledgebaseBean.setReportId(Long.parseLong(reportId));
        aisKnowledgebaseBean.setCollectorCode(operatorId);
        aisKnowledgebaseBean.setOperateDatetime(nousedate);
        AisKnowledgebaseEngine.save(aisKnowledgebaseBean);
        
        Statement stmt = null;
        try {
			stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = " update AIS_KNOWLEDGEBASE set template_exam =(select report_exam from ais_studyreport where report_id="+Long.parseLong(reportId)+"),"
            		+ "template_result =(select report_result from ais_studyreport where report_id="+Long.parseLong(reportId)+")  where knowledgebaseid="+id;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }

    //知识库记录初始化
    public AisKnowledgebaseBean[] getKnowledgeTree(String id,String operatorId) throws Exception {
        String sql = null;
        if(id.equals("-1")){
            sql = " SELECT DISTINCT  KNOWLEDGEBASEID,CASEGROUPDESC,collector_code FROM AIS_KNOWLEDGEBASE WHERE collector_code  = '" + operatorId + "' START WITH CASEGROUPDESC IS NOT NULL CONNECT BY nocycle PRIOR CASEGROUPDESC = KEYDESC";
        }else{
            sql = " SELECT KEYDESC FROM AIS_KNOWLEDGEBASE WHERE CASEGROUPDESC  = '" + id + "'";
        }
        AisKnowledgebaseBean[] beans = AisKnowledgebaseEngine.getBeansFromSql(sql, null);
        return beans;
    }

    //报告详情查询
    public Map getKnowLedgeReportInfo(long id) throws Exception{
    	 Map map = new HashMap();
         Statement stmt = null;
         try {
 			stmt = ServiceManager.getSession().getConnection().createStatement();
             String sql = " select * from AIS_KNOWLEDGEBASE where knowledgebaseid="+id;
             ResultSet rsq = stmt.executeQuery(sql);
             String templateExam = "";
             String templateResult = "";
             if (rsq.next()) {
                 Clob exam = rsq.getClob("TEMPLATE_EXAM");//检查所见
                 Clob result = rsq.getClob("TEMPLATE_RESULT");//诊断结果
                 if (exam != null) {
                     templateExam = exam.getSubString(1, (int) exam.length());
                     System.out.println("CLOB字段的值：" + templateExam);
                 }
                 if (result != null) {
                     templateResult = result.getSubString(1, (int) result.length());
                 }
             }
             map.put("templateExam", templateExam.replace("<br type=\"_moz\" />", "").replace("&nbsp;", ""));
             map.put("templateResult", templateResult.replace("<br type=\"_moz\" />", "").replace("&nbsp;", ""));
         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         } finally {
             DBUtil.release(null, stmt, null);
             ServiceManager.getSession().getConnection().close();
         }
         return map;
    }

    public int checkReportIsRepeat(String reportId) throws Exception{
    	Statement stmt = null;
        ResultSet rsCount = null;
        int total = 0;
        try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = " SELECT COUNT(*) COUNT FROM AIS_KNOWLEDGEBASE WHERE REPORT_ID  = '" + reportId + "'";
	        rsCount = stmt.executeQuery(sql);
	        if (rsCount.next()) {
	            total = rsCount.getInt("COUNT");
	        }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rsCount, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return total;
    }

    public int checkKeydescIsRepeat(String keydesc,String casegroupdescTemp) throws Exception{
    	int total = 0;
    	Statement stmt = null;
        ResultSet rsCount = null;
    	try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = " SELECT COUNT(*) COUNT FROM AIS_KNOWLEDGEBASE WHERE KEYDESC  = '" + keydesc + "' and CASEGROUPDESC='"+ casegroupdescTemp +"'";
	        rsCount = stmt.executeQuery(sql);
        if (rsCount.next()) {
            total = rsCount.getInt("COUNT");
        }
    }catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBUtil.release(rsCount, stmt, null);
        ServiceManager.getSession().getConnection().close();
    }
        return total;
    }
	//根据报告ID取AIS_StudyInfo信息
	public AisStudyInfoBean getAisStudyInfoBean(String reportId){
		StringBuffer condition = new StringBuffer(" 1=1");
		AisStudyInfoBean  aisStudyInfoBean = null; 
		if(StringUtils.isNotBlank(reportId)){
			condition.append(" AND REPORT_ID='"+reportId+"'");
			AisStudyReportBean [] aisStudyReportBeans = null;
			try {
				aisStudyReportBeans = AisStudyReportEngine.getBeans(condition.toString(), null);
				if(aisStudyReportBeans.length>0 && aisStudyReportBeans != null){
					String studyInfoId =  String.valueOf(aisStudyReportBeans[0].getStudyinfoId());
					if(StringUtils.isNotBlank(studyInfoId)){
						StringBuffer condition2= new StringBuffer(" 1=1");
						condition2.append(" AND STUDYINFO_ID='"+studyInfoId+"'");
						AisStudyInfoBean [] aisStudyInfoBeans = null;
						aisStudyInfoBeans = AisStudyInfoEngine.getBeans(condition2.toString(), null);
						if(aisStudyInfoBeans.length>0 && aisStudyInfoBeans != null){
							aisStudyInfoBean = aisStudyInfoBeans[0];
						}

					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aisStudyInfoBean;
	}
	public boolean saveAisStudyMessage(AisStudyInfoBean aisStudyInfoBean,String messageDestuerId,String sourceType,String statusCode){
		boolean errCode=true;
		try{
			Timestamp dateTime=new Timestamp(new Date().getTime());
			AisStudyMessageBean studyMsg = new AisStudyMessageBean();
	    	studyMsg.setMessageId(ServiceUtil.getSequence("SEQ_AIS_STUDYMESSAGE"));
	    	studyMsg.setStudyinfoId(aisStudyInfoBean.getStudyinfoId());
	    	studyMsg.setStatusCode("VERIFY");
	    	studyMsg.setStudyType(sourceType);
	    	studyMsg.setMessageDestlocid(aisStudyInfoBean.getLocId());
	    	studyMsg.setMessageDestuserid("");
	    	studyMsg.setStudyDate(dateTime);
	    	AisStudyMessageEngine.save(studyMsg);
		}catch (Exception e) {
			errCode=false;
			e.printStackTrace();
		}
		return errCode;
	}
	
	public void updateStudyInfo(String statusCode,long studyInfoId) throws Exception{
		//1.修改检查单状态
        AisStudyInfoBean sbean = AisStudyInfoEngine.getBean(studyInfoId);
        sbean.setStudystatusCode(statusCode);
        AisStudyInfoEngine.save(sbean);
	}
	
	private void addLog(Timestamp  stime,long st,String serviceName,String serviceStatus,String xmlReq,String xmlRsp,String errorMsg) throws Exception{
		//1写日志
		long id =ServiceUtil.getSequence("SEQ_AIS_SERVICE_LOG");
		AisServiceLogBean  bean=new AisServiceLogBean();
		bean.setServiceId(id);
		bean.setRequestTime(stime);
		bean.setServiceName(serviceName);
		bean.setServiceRequest(xmlReq);
		bean.setServiceStatus(serviceStatus);
		bean.setServiceResponse(xmlRsp);
		bean.setErrorInfo(errorMsg);
		bean.setTotal(String.valueOf(System.currentTimeMillis()-st));
		bean.setResponseTime(new Timestamp(System.currentTimeMillis()));
		AisServiceLogEngine.save(bean);
	}
}