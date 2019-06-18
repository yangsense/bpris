package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class AiscPatientCheckCountyrpBean extends DataContainer implements DataContainerInterface,IAiscPatientCheckCountyrpValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.AiscPatientCheckCountyrp";



  public final static  String S_ReportNum = "REPORT_NUM";
  public final static  String S_CheckedNum = "CHECKED_NUM";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_ImageNum = "IMAGE_NUM";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_Id = "ID";
  public final static  String S_CollectNum = "COLLECT_NUM";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_RegistNum = "REGIST_NUM";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscPatientCheckCountyrpBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initCheckedNum(long value){
     this.initProperty(S_CheckedNum,new Long(value));
  }
  public  void setCheckedNum(long value){
     this.set(S_CheckedNum,new Long(value));
  }
  public  void setCheckedNumNull(){
     this.set(S_CheckedNum,null);
  }

  public long getCheckedNum(){
        return DataType.getAsLong(this.get(S_CheckedNum));
  
  }
  public long getCheckedNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CheckedNum));
      }

  public void initCityDesc(String value){
     this.initProperty(S_CityDesc,value);
  }
  public  void setCityDesc(String value){
     this.set(S_CityDesc,value);
  }
  public  void setCityDescNull(){
     this.set(S_CityDesc,null);
  }

  public String getCityDesc(){
       return DataType.getAsString(this.get(S_CityDesc));
  
  }
  public String getCityDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CityDesc));
      }

  public void initImageNum(long value){
     this.initProperty(S_ImageNum,new Long(value));
  }
  public  void setImageNum(long value){
     this.set(S_ImageNum,new Long(value));
  }
  public  void setImageNumNull(){
     this.set(S_ImageNum,null);
  }

  public long getImageNum(){
        return DataType.getAsLong(this.get(S_ImageNum));
  
  }
  public long getImageNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ImageNum));
      }

  public void initCityCode(String value){
     this.initProperty(S_CityCode,value);
  }
  public  void setCityCode(String value){
     this.set(S_CityCode,value);
  }
  public  void setCityCodeNull(){
     this.set(S_CityCode,null);
  }

  public String getCityCode(){
       return DataType.getAsString(this.get(S_CityCode));
  
  }
  public String getCityCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CityCode));
      }

  public void initCheckDate(String value){
     this.initProperty(S_CheckDate,value);
  }
  public  void setCheckDate(String value){
     this.set(S_CheckDate,value);
  }
  public  void setCheckDateNull(){
     this.set(S_CheckDate,null);
  }

  public String getCheckDate(){
       return DataType.getAsString(this.get(S_CheckDate));
  
  }
  public String getCheckDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CheckDate));
      }

  public void initCountyDesc(String value){
     this.initProperty(S_CountyDesc,value);
  }
  public  void setCountyDesc(String value){
     this.set(S_CountyDesc,value);
  }
  public  void setCountyDescNull(){
     this.set(S_CountyDesc,null);
  }

  public String getCountyDesc(){
       return DataType.getAsString(this.get(S_CountyDesc));
  
  }
  public String getCountyDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CountyDesc));
      }

  public void initId(long value){
     this.initProperty(S_Id,new Long(value));
  }
  public  void setId(long value){
     this.set(S_Id,new Long(value));
  }
  public  void setIdNull(){
     this.set(S_Id,null);
  }

  public long getId(){
        return DataType.getAsLong(this.get(S_Id));
  
  }
  public long getIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Id));
      }

  public void initCollectNum(long value){
     this.initProperty(S_CollectNum,new Long(value));
  }
  public  void setCollectNum(long value){
     this.set(S_CollectNum,new Long(value));
  }
  public  void setCollectNumNull(){
     this.set(S_CollectNum,null);
  }

  public long getCollectNum(){
        return DataType.getAsLong(this.get(S_CollectNum));
  
  }
  public long getCollectNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CollectNum));
      }

  public void initCountyCode(String value){
     this.initProperty(S_CountyCode,value);
  }
  public  void setCountyCode(String value){
     this.set(S_CountyCode,value);
  }
  public  void setCountyCodeNull(){
     this.set(S_CountyCode,null);
  }

  public String getCountyCode(){
       return DataType.getAsString(this.get(S_CountyCode));
  
  }
  public String getCountyCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CountyCode));
      }

  public void initRegistNum(long value){
     this.initProperty(S_RegistNum,new Long(value));
  }
  public  void setRegistNum(long value){
     this.set(S_RegistNum,new Long(value));
  }
  public  void setRegistNumNull(){
     this.set(S_RegistNum,null);
  }

  public long getRegistNum(){
        return DataType.getAsLong(this.get(S_RegistNum));
  
  }
  public long getRegistNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RegistNum));
      }


 
 }

