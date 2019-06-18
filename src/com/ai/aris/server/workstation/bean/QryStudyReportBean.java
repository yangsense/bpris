package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryStudyReportBean extends DataContainer implements DataContainerInterface,IQryStudyReportValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryStudyReport";



  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryStudyReportBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initReportDatetime(Timestamp value){
     this.initProperty(S_ReportDatetime,value);
  }
  public  void setReportDatetime(Timestamp value){
     this.set(S_ReportDatetime,value);
  }
  public  void setReportDatetimeNull(){
     this.set(S_ReportDatetime,null);
  }

  public Timestamp getReportDatetime(){
        return DataType.getAsDateTime(this.get(S_ReportDatetime));
  
  }
  public Timestamp getReportDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportDatetime));
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


 
 }

