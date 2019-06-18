package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

import java.sql.Timestamp;

public class SysOperatorLogBean extends DataContainer implements DataContainerInterface,ISysOperatorLogValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.SysOperatorLog";



  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_LogId = "LOG_ID";
  public final static  String S_OperatorType = "OPERATOR_TYPE";
  public final static  String S_OperatorDesc = "OPERATOR_DESC";
  public final static  String S_OperatorDate = "OPERATOR_DATE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SysOperatorLogBean() throws AIException {
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException {
   return S_TYPE;
 }

 @Override
public void setObjectType(ObjectType value) throws AIException {
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOperatorCode(String value){
     this.initProperty(S_OperatorCode,value);
  }
  public  void setOperatorCode(String value){
     this.set(S_OperatorCode,value);
  }
  public  void setOperatorCodeNull(){
     this.set(S_OperatorCode,null);
  }

  public String getOperatorCode(){
       return DataType.getAsString(this.get(S_OperatorCode));
  
  }
  public String getOperatorCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorCode));
      }

  public void initLogId(long value){
     this.initProperty(S_LogId,new Long(value));
  }
  public  void setLogId(long value){
     this.set(S_LogId,new Long(value));
  }
  public  void setLogIdNull(){
     this.set(S_LogId,null);
  }

  public long getLogId(){
        return DataType.getAsLong(this.get(S_LogId));
  
  }
  public long getLogIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LogId));
      }

  public void initOperatorType(String value){
     this.initProperty(S_OperatorType,value);
  }
  public  void setOperatorType(String value){
     this.set(S_OperatorType,value);
  }
  public  void setOperatorTypeNull(){
     this.set(S_OperatorType,null);
  }

  public String getOperatorType(){
       return DataType.getAsString(this.get(S_OperatorType));
  
  }
  public String getOperatorTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorType));
      }

  public void initOperatorDesc(String value){
     this.initProperty(S_OperatorDesc,value);
  }
  public  void setOperatorDesc(String value){
     this.set(S_OperatorDesc,value);
  }
  public  void setOperatorDescNull(){
     this.set(S_OperatorDesc,null);
  }

  public String getOperatorDesc(){
       return DataType.getAsString(this.get(S_OperatorDesc));
  
  }
  public String getOperatorDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorDesc));
      }

  public void initOperatorDate(Timestamp value){
     this.initProperty(S_OperatorDate,value);
  }
  public  void setOperatorDate(Timestamp value){
     this.set(S_OperatorDate,value);
  }
  public  void setOperatorDateNull(){
     this.set(S_OperatorDate,null);
  }

  public Timestamp getOperatorDate(){
        return DataType.getAsDateTime(this.get(S_OperatorDate));
  
  }
  public Timestamp getOperatorDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_OperatorDate));
      }


 
 }

