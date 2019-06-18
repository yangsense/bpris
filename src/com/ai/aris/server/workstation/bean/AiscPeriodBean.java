package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AiscPeriodBean extends DataContainer implements DataContainerInterface,IAiscPeriodValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AiscPeriod";



  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_PeriodStarttime = "PERIOD_STARTTIME";
  public final static  String S_PeriodDesc = "PERIOD_DESC";
  public final static  String S_Id = "ID";
  public final static  String S_PeriodId = "PERIOD_ID";
  public final static  String S_PeriodEndtime = "PERIOD_ENDTIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscPeriodBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCreateTime(Timestamp value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(Timestamp value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public Timestamp getCreateTime(){
        return DataType.getAsDateTime(this.get(S_CreateTime));
  
  }
  public Timestamp getCreateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTime));
      }

  public void initPeriodStarttime(String value){
     this.initProperty(S_PeriodStarttime,value);
  }
  public  void setPeriodStarttime(String value){
     this.set(S_PeriodStarttime,value);
  }
  public  void setPeriodStarttimeNull(){
     this.set(S_PeriodStarttime,null);
  }

  public String getPeriodStarttime(){
       return DataType.getAsString(this.get(S_PeriodStarttime));
  
  }
  public String getPeriodStarttimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PeriodStarttime));
      }

  public void initPeriodDesc(String value){
     this.initProperty(S_PeriodDesc,value);
  }
  public  void setPeriodDesc(String value){
     this.set(S_PeriodDesc,value);
  }
  public  void setPeriodDescNull(){
     this.set(S_PeriodDesc,null);
  }

  public String getPeriodDesc(){
       return DataType.getAsString(this.get(S_PeriodDesc));
  
  }
  public String getPeriodDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PeriodDesc));
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

  public void initPeriodId(String value){
     this.initProperty(S_PeriodId,value);
  }
  public  void setPeriodId(String value){
     this.set(S_PeriodId,value);
  }
  public  void setPeriodIdNull(){
     this.set(S_PeriodId,null);
  }

  public String getPeriodId(){
       return DataType.getAsString(this.get(S_PeriodId));
  
  }
  public String getPeriodIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PeriodId));
      }

  public void initPeriodEndtime(String value){
     this.initProperty(S_PeriodEndtime,value);
  }
  public  void setPeriodEndtime(String value){
     this.set(S_PeriodEndtime,value);
  }
  public  void setPeriodEndtimeNull(){
     this.set(S_PeriodEndtime,null);
  }

  public String getPeriodEndtime(){
       return DataType.getAsString(this.get(S_PeriodEndtime));
  
  }
  public String getPeriodEndtimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PeriodEndtime));
      }


 
 }

