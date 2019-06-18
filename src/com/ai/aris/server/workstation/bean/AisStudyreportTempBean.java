package com.ai.aris.server.workstation.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

import java.sql.Timestamp;

public class AisStudyreportTempBean extends DataContainer implements DataContainerInterface,IAisStudyreportTempValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisStudyreportTemp";



  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyreportTempBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyinfoId(String value){
     this.initProperty(S_StudyinfoId,value);
  }
  public  void setStudyinfoId(String value){
     this.set(S_StudyinfoId,value);
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public String getStudyinfoId(){
       return DataType.getAsString(this.get(S_StudyinfoId));
  
  }
  public String getStudyinfoIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyinfoId));
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


 
 }

