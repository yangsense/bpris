package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryDoctorWorkloadBean extends DataContainer implements DataContainerInterface,IQryDoctorWorkloadValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryDoctorWorkload";



  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_ReportDoctorid = "REPORT_DOCTORID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ReportDate = "REPORT_DATE";
  public final static  String S_Zb = "ZB";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_ReportId = "REPORT_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryDoctorWorkloadBean() throws AIException{
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

  public void initLocId(long value){
     this.initProperty(S_LocId,new Long(value));
  }
  public  void setLocId(long value){
     this.set(S_LocId,new Long(value));
  }
  public  void setLocIdNull(){
     this.set(S_LocId,null);
  }

  public long getLocId(){
        return DataType.getAsLong(this.get(S_LocId));
  
  }
  public long getLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocId));
      }

  public void initReportDate(String value){
     this.initProperty(S_ReportDate,value);
  }
  public  void setReportDate(String value){
     this.set(S_ReportDate,value);
  }
  public  void setReportDateNull(){
     this.set(S_ReportDate,null);
  }

  public String getReportDate(){
       return DataType.getAsString(this.get(S_ReportDate));
  
  }
  public String getReportDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDate));
      }

  public void initZb(String value){
     this.initProperty(S_Zb,value);
  }
  public  void setZb(String value){
     this.set(S_Zb,value);
  }
  public  void setZbNull(){
     this.set(S_Zb,null);
  }

  public String getZb(){
       return DataType.getAsString(this.get(S_Zb));
  
  }
  public String getZbInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Zb));
      }

  public void initStudyitemPrice(long value){
     this.initProperty(S_StudyitemPrice,new Long(value));
  }
  public  void setStudyitemPrice(long value){
     this.set(S_StudyitemPrice,new Long(value));
  }
  public  void setStudyitemPriceNull(){
     this.set(S_StudyitemPrice,null);
  }

  public long getStudyitemPrice(){
        return DataType.getAsLong(this.get(S_StudyitemPrice));
  
  }
  public long getStudyitemPriceInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyitemPrice));
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


 
 }

