package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryEquiWorkChartsBean extends DataContainer implements DataContainerInterface,IQryEquiWorkChartsValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryEquiWorkCharts";



  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_ReportId = "REPORT_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryEquiWorkChartsBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initCareprovName(String value){
     this.initProperty(S_CareprovName,value);
  }
  public  void setCareprovName(String value){
     this.set(S_CareprovName,value);
  }
  public  void setCareprovNameNull(){
     this.set(S_CareprovName,null);
  }

  public String getCareprovName(){
       return DataType.getAsString(this.get(S_CareprovName));
  
  }
  public String getCareprovNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CareprovName));
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

