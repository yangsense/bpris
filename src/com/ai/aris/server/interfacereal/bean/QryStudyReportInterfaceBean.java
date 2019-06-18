package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class QryStudyReportInterfaceBean extends DataContainer implements DataContainerInterface,IQryStudyReportInterfaceValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.QryStudyReportInterface";



  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_ReportExammethod = "REPORT_EXAMMETHOD";
  public final static  String S_ReportDoctorid = "REPORT_DOCTORID";
  public final static  String S_ReportRecord = "REPORT_RECORD";
  public final static  String S_ReportIssent = "REPORT_ISSENT";
  public final static  String S_Status = "STATUS";
  public final static  String S_ReportFinaldatetime = "REPORT_FINALDATETIME";
  public final static  String S_ReportAcrcode = "REPORT_ACRCODE";
  public final static  String S_ReportLock = "REPORT_LOCK";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportHistoryId = "REPORT_HISTORY_ID";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportUncompletedreason = "REPORT_UNCOMPLETEDREASON";
  public final static  String S_ReportIscompleted = "REPORT_ISCOMPLETED";
  public final static  String S_ReportPrintdatetime = "REPORT_PRINTDATETIME";
  public final static  String S_ReportIsprinted = "REPORT_ISPRINTED";
  public final static  String S_ReportFinaldoctorid = "REPORT_FINALDOCTORID";
  public final static  String S_ReportIsvisible = "REPORT_ISVISIBLE";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ContrastsRemark = "CONTRASTS_REMARK";
  public final static  String S_ReportIcd10 = "REPORT_ICD10";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_ReportVerifydatetime = "REPORT_VERIFYDATETIME";
  public final static  String S_ReportPrintcareprovid = "REPORT_PRINTCAREPROVID";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_ReportNum = "REPORT_NUM";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportId = "REPORT_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryStudyReportInterfaceBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initReportIspositive(long value){
     this.initProperty(S_ReportIspositive,new Long(value));
  }
  public  void setReportIspositive(long value){
     this.set(S_ReportIspositive,new Long(value));
  }
  public  void setReportIspositiveNull(){
     this.set(S_ReportIspositive,null);
  }

  public long getReportIspositive(){
        return DataType.getAsLong(this.get(S_ReportIspositive));
  
  }
  public long getReportIspositiveInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIspositive));
      }

  public void initReportExammethod(String value){
     this.initProperty(S_ReportExammethod,value);
  }
  public  void setReportExammethod(String value){
     this.set(S_ReportExammethod,value);
  }
  public  void setReportExammethodNull(){
     this.set(S_ReportExammethod,null);
  }

  public String getReportExammethod(){
       return DataType.getAsString(this.get(S_ReportExammethod));
  
  }
  public String getReportExammethodInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportExammethod));
      }

  public void initReportDoctorid(String value){
     this.initProperty(S_ReportDoctorid,value);
  }
  public  void setReportDoctorid(String value){
     this.set(S_ReportDoctorid,value);
  }
  public  void setReportDoctoridNull(){
     this.set(S_ReportDoctorid,null);
  }

  public String getReportDoctorid(){
       return DataType.getAsString(this.get(S_ReportDoctorid));
  
  }
  public String getReportDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDoctorid));
      }

  public void initReportRecord(String value){
     this.initProperty(S_ReportRecord,value);
  }
  public  void setReportRecord(String value){
     this.set(S_ReportRecord,value);
  }
  public  void setReportRecordNull(){
     this.set(S_ReportRecord,null);
  }

  public String getReportRecord(){
       return DataType.getAsString(this.get(S_ReportRecord));
  
  }
  public String getReportRecordInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportRecord));
      }

  public void initReportIssent(long value){
     this.initProperty(S_ReportIssent,new Long(value));
  }
  public  void setReportIssent(long value){
     this.set(S_ReportIssent,new Long(value));
  }
  public  void setReportIssentNull(){
     this.set(S_ReportIssent,null);
  }

  public long getReportIssent(){
        return DataType.getAsLong(this.get(S_ReportIssent));
  
  }
  public long getReportIssentInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIssent));
      }

  public void initStatus(String value){
     this.initProperty(S_Status,value);
  }
  public  void setStatus(String value){
     this.set(S_Status,value);
  }
  public  void setStatusNull(){
     this.set(S_Status,null);
  }

  public String getStatus(){
       return DataType.getAsString(this.get(S_Status));
  
  }
  public String getStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Status));
      }

  public void initReportFinaldatetime(Timestamp value){
     this.initProperty(S_ReportFinaldatetime,value);
  }
  public  void setReportFinaldatetime(Timestamp value){
     this.set(S_ReportFinaldatetime,value);
  }
  public  void setReportFinaldatetimeNull(){
     this.set(S_ReportFinaldatetime,null);
  }

  public Timestamp getReportFinaldatetime(){
        return DataType.getAsDateTime(this.get(S_ReportFinaldatetime));
  
  }
  public Timestamp getReportFinaldatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportFinaldatetime));
      }

  public void initReportAcrcode(String value){
     this.initProperty(S_ReportAcrcode,value);
  }
  public  void setReportAcrcode(String value){
     this.set(S_ReportAcrcode,value);
  }
  public  void setReportAcrcodeNull(){
     this.set(S_ReportAcrcode,null);
  }

  public String getReportAcrcode(){
       return DataType.getAsString(this.get(S_ReportAcrcode));
  
  }
  public String getReportAcrcodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportAcrcode));
      }

  public void initReportLock(long value){
     this.initProperty(S_ReportLock,new Long(value));
  }
  public  void setReportLock(long value){
     this.set(S_ReportLock,new Long(value));
  }
  public  void setReportLockNull(){
     this.set(S_ReportLock,null);
  }

  public long getReportLock(){
        return DataType.getAsLong(this.get(S_ReportLock));
  
  }
  public long getReportLockInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportLock));
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

  public void initReportHistoryId(long value){
     this.initProperty(S_ReportHistoryId,new Long(value));
  }
  public  void setReportHistoryId(long value){
     this.set(S_ReportHistoryId,new Long(value));
  }
  public  void setReportHistoryIdNull(){
     this.set(S_ReportHistoryId,null);
  }

  public long getReportHistoryId(){
        return DataType.getAsLong(this.get(S_ReportHistoryId));
  
  }
  public long getReportHistoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportHistoryId));
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

  public void initReportUncompletedreason(String value){
     this.initProperty(S_ReportUncompletedreason,value);
  }
  public  void setReportUncompletedreason(String value){
     this.set(S_ReportUncompletedreason,value);
  }
  public  void setReportUncompletedreasonNull(){
     this.set(S_ReportUncompletedreason,null);
  }

  public String getReportUncompletedreason(){
       return DataType.getAsString(this.get(S_ReportUncompletedreason));
  
  }
  public String getReportUncompletedreasonInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportUncompletedreason));
      }

  public void initReportIscompleted(long value){
     this.initProperty(S_ReportIscompleted,new Long(value));
  }
  public  void setReportIscompleted(long value){
     this.set(S_ReportIscompleted,new Long(value));
  }
  public  void setReportIscompletedNull(){
     this.set(S_ReportIscompleted,null);
  }

  public long getReportIscompleted(){
        return DataType.getAsLong(this.get(S_ReportIscompleted));
  
  }
  public long getReportIscompletedInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIscompleted));
      }

  public void initReportPrintdatetime(Timestamp value){
     this.initProperty(S_ReportPrintdatetime,value);
  }
  public  void setReportPrintdatetime(Timestamp value){
     this.set(S_ReportPrintdatetime,value);
  }
  public  void setReportPrintdatetimeNull(){
     this.set(S_ReportPrintdatetime,null);
  }

  public Timestamp getReportPrintdatetime(){
        return DataType.getAsDateTime(this.get(S_ReportPrintdatetime));
  
  }
  public Timestamp getReportPrintdatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportPrintdatetime));
      }

  public void initReportIsprinted(long value){
     this.initProperty(S_ReportIsprinted,new Long(value));
  }
  public  void setReportIsprinted(long value){
     this.set(S_ReportIsprinted,new Long(value));
  }
  public  void setReportIsprintedNull(){
     this.set(S_ReportIsprinted,null);
  }

  public long getReportIsprinted(){
        return DataType.getAsLong(this.get(S_ReportIsprinted));
  
  }
  public long getReportIsprintedInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIsprinted));
      }

  public void initReportFinaldoctorid(String value){
     this.initProperty(S_ReportFinaldoctorid,value);
  }
  public  void setReportFinaldoctorid(String value){
     this.set(S_ReportFinaldoctorid,value);
  }
  public  void setReportFinaldoctoridNull(){
     this.set(S_ReportFinaldoctorid,null);
  }

  public String getReportFinaldoctorid(){
       return DataType.getAsString(this.get(S_ReportFinaldoctorid));
  
  }
  public String getReportFinaldoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportFinaldoctorid));
      }

  public void initReportIsvisible(long value){
     this.initProperty(S_ReportIsvisible,new Long(value));
  }
  public  void setReportIsvisible(long value){
     this.set(S_ReportIsvisible,new Long(value));
  }
  public  void setReportIsvisibleNull(){
     this.set(S_ReportIsvisible,null);
  }

  public long getReportIsvisible(){
        return DataType.getAsLong(this.get(S_ReportIsvisible));
  
  }
  public long getReportIsvisibleInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIsvisible));
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

  public void initContrastsRemark(String value){
     this.initProperty(S_ContrastsRemark,value);
  }
  public  void setContrastsRemark(String value){
     this.set(S_ContrastsRemark,value);
  }
  public  void setContrastsRemarkNull(){
     this.set(S_ContrastsRemark,null);
  }

  public String getContrastsRemark(){
       return DataType.getAsString(this.get(S_ContrastsRemark));
  
  }
  public String getContrastsRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ContrastsRemark));
      }

  public void initReportIcd10(String value){
     this.initProperty(S_ReportIcd10,value);
  }
  public  void setReportIcd10(String value){
     this.set(S_ReportIcd10,value);
  }
  public  void setReportIcd10Null(){
     this.set(S_ReportIcd10,null);
  }

  public String getReportIcd10(){
       return DataType.getAsString(this.get(S_ReportIcd10));
  
  }
  public String getReportIcd10InitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportIcd10));
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

  public void initStudystatusCode(String value){
     this.initProperty(S_StudystatusCode,value);
  }
  public  void setStudystatusCode(String value){
     this.set(S_StudystatusCode,value);
  }
  public  void setStudystatusCodeNull(){
     this.set(S_StudystatusCode,null);
  }

  public String getStudystatusCode(){
       return DataType.getAsString(this.get(S_StudystatusCode));
  
  }
  public String getStudystatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudystatusCode));
      }

  public void initReportVerifydatetime(Timestamp value){
     this.initProperty(S_ReportVerifydatetime,value);
  }
  public  void setReportVerifydatetime(Timestamp value){
     this.set(S_ReportVerifydatetime,value);
  }
  public  void setReportVerifydatetimeNull(){
     this.set(S_ReportVerifydatetime,null);
  }

  public Timestamp getReportVerifydatetime(){
        return DataType.getAsDateTime(this.get(S_ReportVerifydatetime));
  
  }
  public Timestamp getReportVerifydatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportVerifydatetime));
      }

  public void initReportPrintcareprovid(String value){
     this.initProperty(S_ReportPrintcareprovid,value);
  }
  public  void setReportPrintcareprovid(String value){
     this.set(S_ReportPrintcareprovid,value);
  }
  public  void setReportPrintcareprovidNull(){
     this.set(S_ReportPrintcareprovid,null);
  }

  public String getReportPrintcareprovid(){
       return DataType.getAsString(this.get(S_ReportPrintcareprovid));
  
  }
  public String getReportPrintcareprovidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportPrintcareprovid));
      }

  public void initStudyOperationtime(Timestamp value){
     this.initProperty(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtime(Timestamp value){
     this.set(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtimeNull(){
     this.set(S_StudyOperationtime,null);
  }

  public Timestamp getStudyOperationtime(){
        return DataType.getAsDateTime(this.get(S_StudyOperationtime));
  
  }
  public Timestamp getStudyOperationtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyOperationtime));
      }

  public void initReportNum(long value){
     this.initProperty(S_ReportNum,new Long(value));
  }
  public  void setReportNum(long value){
     this.set(S_ReportNum,new Long(value));
  }
  public  void setReportNumNull(){
     this.set(S_ReportNum,null);
  }

  public long getReportNum(){
        return DataType.getAsLong(this.get(S_ReportNum));
  
  }
  public long getReportNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportNum));
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

  public void initReportVerifydoctorid(String value){
     this.initProperty(S_ReportVerifydoctorid,value);
  }
  public  void setReportVerifydoctorid(String value){
     this.set(S_ReportVerifydoctorid,value);
  }
  public  void setReportVerifydoctoridNull(){
     this.set(S_ReportVerifydoctorid,null);
  }

  public String getReportVerifydoctorid(){
       return DataType.getAsString(this.get(S_ReportVerifydoctorid));
  
  }
  public String getReportVerifydoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifydoctorid));
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

  public void initReportId(long value){
     this.initProperty(S_ReportId,new Long(value));
  }
  public  void setReportId(long value){
     this.set(S_ReportId,new Long(value));
  }
  public  void setReportIdNull(){
     this.set(S_ReportId,null);
  }

  public long getReportId(){
        return DataType.getAsLong(this.get(S_ReportId));
  
  }
  public long getReportIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportId));
      }


 
 }

