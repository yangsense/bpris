package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QuerySysStationBean extends DataContainer implements DataContainerInterface,IQuerySysStationValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QuerySysStation";



  public final static  String S_StationCode = "STATION_CODE";
  public final static  String S_StationName = "STATION_NAME";
  public final static  String S_Remark = "REMARK";
  public final static  String S_SysType = "SYS_TYPE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QuerySysStationBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStationCode(String value){
     this.initProperty(S_StationCode,value);
  }
  public  void setStationCode(String value){
     this.set(S_StationCode,value);
  }
  public  void setStationCodeNull(){
     this.set(S_StationCode,null);
  }

  public String getStationCode(){
       return DataType.getAsString(this.get(S_StationCode));
  
  }
  public String getStationCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StationCode));
      }

  public void initStationName(String value){
     this.initProperty(S_StationName,value);
  }
  public  void setStationName(String value){
     this.set(S_StationName,value);
  }
  public  void setStationNameNull(){
     this.set(S_StationName,null);
  }

  public String getStationName(){
       return DataType.getAsString(this.get(S_StationName));
  
  }
  public String getStationNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StationName));
      }

  public void initRemark(String value){
     this.initProperty(S_Remark,value);
  }
  public  void setRemark(String value){
     this.set(S_Remark,value);
  }
  public  void setRemarkNull(){
     this.set(S_Remark,null);
  }

  public String getRemark(){
       return DataType.getAsString(this.get(S_Remark));
  
  }
  public String getRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Remark));
      }

  public void initSysType(long value){
     this.initProperty(S_SysType,new Long(value));
  }
  public  void setSysType(long value){
     this.set(S_SysType,new Long(value));
  }
  public  void setSysTypeNull(){
     this.set(S_SysType,null);
  }

  public long getSysType(){
        return DataType.getAsLong(this.get(S_SysType));
  
  }
  public long getSysTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SysType));
      }


 
 }

