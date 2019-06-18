package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryEquiWorkloadBean extends DataContainer implements DataContainerInterface,IQryEquiWorkloadValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryEquiWorkload";



  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_CStudyDatetime = "C_STUDY_DATETIME";
  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Zb = "ZB";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryEquiWorkloadBean() throws AIException{
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

  public void initCStudyDatetime(String value){
     this.initProperty(S_CStudyDatetime,value);
  }
  public  void setCStudyDatetime(String value){
     this.set(S_CStudyDatetime,value);
  }
  public  void setCStudyDatetimeNull(){
     this.set(S_CStudyDatetime,null);
  }

  public String getCStudyDatetime(){
       return DataType.getAsString(this.get(S_CStudyDatetime));
  
  }
  public String getCStudyDatetimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyDatetime));
      }

  public void initStudyitemNumber(long value){
     this.initProperty(S_StudyitemNumber,new Long(value));
  }
  public  void setStudyitemNumber(long value){
     this.set(S_StudyitemNumber,new Long(value));
  }
  public  void setStudyitemNumberNull(){
     this.set(S_StudyitemNumber,null);
  }

  public long getStudyitemNumber(){
        return DataType.getAsLong(this.get(S_StudyitemNumber));
  
  }
  public long getStudyitemNumberInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyitemNumber));
      }

  public void initEquipmentDesc(String value){
     this.initProperty(S_EquipmentDesc,value);
  }
  public  void setEquipmentDesc(String value){
     this.set(S_EquipmentDesc,value);
  }
  public  void setEquipmentDescNull(){
     this.set(S_EquipmentDesc,null);
  }

  public String getEquipmentDesc(){
       return DataType.getAsString(this.get(S_EquipmentDesc));
  
  }
  public String getEquipmentDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentDesc));
      }

  public void initModalityId(long value){
     this.initProperty(S_ModalityId,new Long(value));
  }
  public  void setModalityId(long value){
     this.set(S_ModalityId,new Long(value));
  }
  public  void setModalityIdNull(){
     this.set(S_ModalityId,null);
  }

  public long getModalityId(){
        return DataType.getAsLong(this.get(S_ModalityId));
  
  }
  public long getModalityIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ModalityId));
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

