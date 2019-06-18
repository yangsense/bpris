package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryReportBrowseListBean extends DataContainer implements DataContainerInterface,IQryReportBrowseListValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryReportBrowseList";



  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_ReportFinaldoctorname = "REPORT_FINALDOCTORNAME";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_CStudyStarttime = "C_STUDY_STARTTIME";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportVerifytime = "REPORT_VERIFYTIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryReportBrowseListBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyItemdesc(String value){
     this.initProperty(S_StudyItemdesc,value);
  }
  public  void setStudyItemdesc(String value){
     this.set(S_StudyItemdesc,value);
  }
  public  void setStudyItemdescNull(){
     this.set(S_StudyItemdesc,null);
  }

  public String getStudyItemdesc(){
       return DataType.getAsString(this.get(S_StudyItemdesc));
  
  }
  public String getStudyItemdescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyItemdesc));
      }

  public void initPatientId(String value){
     this.initProperty(S_PatientId,value);
  }
  public  void setPatientId(String value){
     this.set(S_PatientId,value);
  }
  public  void setPatientIdNull(){
     this.set(S_PatientId,null);
  }

  public String getPatientId(){
       return DataType.getAsString(this.get(S_PatientId));
  
  }
  public String getPatientIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientId));
      }

  public void initPatientInpatientid(String value){
     this.initProperty(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientid(String value){
     this.set(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientidNull(){
     this.set(S_PatientInpatientid,null);
  }

  public String getPatientInpatientid(){
       return DataType.getAsString(this.get(S_PatientInpatientid));
  
  }
  public String getPatientInpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientInpatientid));
      }

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
      }

  public void initReportFinaldoctorname(String value){
     this.initProperty(S_ReportFinaldoctorname,value);
  }
  public  void setReportFinaldoctorname(String value){
     this.set(S_ReportFinaldoctorname,value);
  }
  public  void setReportFinaldoctornameNull(){
     this.set(S_ReportFinaldoctorname,null);
  }

  public String getReportFinaldoctorname(){
       return DataType.getAsString(this.get(S_ReportFinaldoctorname));
  
  }
  public String getReportFinaldoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportFinaldoctorname));
      }

  public void initReportExam(String value){
     this.initProperty(S_ReportExam,value);
  }
  public  void setReportExam(String value){
     this.set(S_ReportExam,value);
  }
  public  void setReportExamNull(){
     this.set(S_ReportExam,null);
  }

  public String getReportExam(){
       return DataType.getAsString(this.get(S_ReportExam));
  
  }
  public String getReportExamInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportExam));
      }

  public void initReportVerifydoctorname(String value){
     this.initProperty(S_ReportVerifydoctorname,value);
  }
  public  void setReportVerifydoctorname(String value){
     this.set(S_ReportVerifydoctorname,value);
  }
  public  void setReportVerifydoctornameNull(){
     this.set(S_ReportVerifydoctorname,null);
  }

  public String getReportVerifydoctorname(){
       return DataType.getAsString(this.get(S_ReportVerifydoctorname));
  
  }
  public String getReportVerifydoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifydoctorname));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }

  public void initStudyHaveimage(long value){
     this.initProperty(S_StudyHaveimage,new Long(value));
  }
  public  void setStudyHaveimage(long value){
     this.set(S_StudyHaveimage,new Long(value));
  }
  public  void setStudyHaveimageNull(){
     this.set(S_StudyHaveimage,null);
  }

  public long getStudyHaveimage(){
        return DataType.getAsLong(this.get(S_StudyHaveimage));
  
  }
  public long getStudyHaveimageInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyHaveimage));
      }

  public void initStudyAccnumber(String value){
     this.initProperty(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumber(String value){
     this.set(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumberNull(){
     this.set(S_StudyAccnumber,null);
  }

  public String getStudyAccnumber(){
       return DataType.getAsString(this.get(S_StudyAccnumber));
  
  }
  public String getStudyAccnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAccnumber));
      }

  public void initPatientOutpatientid(String value){
     this.initProperty(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientid(String value){
     this.set(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientidNull(){
     this.set(S_PatientOutpatientid,null);
  }

  public String getPatientOutpatientid(){
       return DataType.getAsString(this.get(S_PatientOutpatientid));
  
  }
  public String getPatientOutpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientOutpatientid));
      }

  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
      }

  public void initReportDoctorname(String value){
     this.initProperty(S_ReportDoctorname,value);
  }
  public  void setReportDoctorname(String value){
     this.set(S_ReportDoctorname,value);
  }
  public  void setReportDoctornameNull(){
     this.set(S_ReportDoctorname,null);
  }

  public String getReportDoctorname(){
       return DataType.getAsString(this.get(S_ReportDoctorname));
  
  }
  public String getReportDoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDoctorname));
      }

  public void initCStudyStarttime(String value){
     this.initProperty(S_CStudyStarttime,value);
  }
  public  void setCStudyStarttime(String value){
     this.set(S_CStudyStarttime,value);
  }
  public  void setCStudyStarttimeNull(){
     this.set(S_CStudyStarttime,null);
  }

  public String getCStudyStarttime(){
       return DataType.getAsString(this.get(S_CStudyStarttime));
  
  }
  public String getCStudyStarttimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyStarttime));
      }

  public void initReportResult(String value){
     this.initProperty(S_ReportResult,value);
  }
  public  void setReportResult(String value){
     this.set(S_ReportResult,value);
  }
  public  void setReportResultNull(){
     this.set(S_ReportResult,null);
  }

  public String getReportResult(){
       return DataType.getAsString(this.get(S_ReportResult));
  
  }
  public String getReportResultInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportResult));
      }

  public void initStudyHavereport(long value){
     this.initProperty(S_StudyHavereport,new Long(value));
  }
  public  void setStudyHavereport(long value){
     this.set(S_StudyHavereport,new Long(value));
  }
  public  void setStudyHavereportNull(){
     this.set(S_StudyHavereport,null);
  }

  public long getStudyHavereport(){
        return DataType.getAsLong(this.get(S_StudyHavereport));
  
  }
  public long getStudyHavereportInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyHavereport));
      }

  public void initReportRemark(String value){
     this.initProperty(S_ReportRemark,value);
  }
  public  void setReportRemark(String value){
     this.set(S_ReportRemark,value);
  }
  public  void setReportRemarkNull(){
     this.set(S_ReportRemark,null);
  }

  public String getReportRemark(){
       return DataType.getAsString(this.get(S_ReportRemark));
  
  }
  public String getReportRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportRemark));
      }

  public void initReportDatetime(String value){
     this.initProperty(S_ReportDatetime,value);
  }
  public  void setReportDatetime(String value){
     this.set(S_ReportDatetime,value);
  }
  public  void setReportDatetimeNull(){
     this.set(S_ReportDatetime,null);
  }

  public String getReportDatetime(){
       return DataType.getAsString(this.get(S_ReportDatetime));
  
  }
  public String getReportDatetimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDatetime));
      }

  public void initReportVerifytime(String value){
     this.initProperty(S_ReportVerifytime,value);
  }
  public  void setReportVerifytime(String value){
     this.set(S_ReportVerifytime,value);
  }
  public  void setReportVerifytimeNull(){
     this.set(S_ReportVerifytime,null);
  }

  public String getReportVerifytime(){
       return DataType.getAsString(this.get(S_ReportVerifytime));
  
  }
  public String getReportVerifytimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifytime));
      }


 
 }

