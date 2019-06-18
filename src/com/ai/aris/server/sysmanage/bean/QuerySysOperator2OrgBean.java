package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QuerySysOperator2OrgBean extends DataContainer implements DataContainerInterface,IQuerySysOperator2OrgValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QuerySysOperator2Org";



  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_StationCode = "STATION_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrgType = "ORG_TYPE";
  public final static  String S_StationName = "STATION_NAME";
  public final static  String S_ParentOrgId = "PARENT_ORG_ID";
  public final static  String S_OrgLevel = "ORG_LEVEL";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QuerySysOperator2OrgBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
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

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
      }

  public void initOrgType(String value){
     this.initProperty(S_OrgType,value);
  }
  public  void setOrgType(String value){
     this.set(S_OrgType,value);
  }
  public  void setOrgTypeNull(){
     this.set(S_OrgType,null);
  }

  public String getOrgType(){
       return DataType.getAsString(this.get(S_OrgType));
  
  }
  public String getOrgTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgType));
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

  public void initParentOrgId(String value){
     this.initProperty(S_ParentOrgId,value);
  }
  public  void setParentOrgId(String value){
     this.set(S_ParentOrgId,value);
  }
  public  void setParentOrgIdNull(){
     this.set(S_ParentOrgId,null);
  }

  public String getParentOrgId(){
       return DataType.getAsString(this.get(S_ParentOrgId));
  
  }
  public String getParentOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ParentOrgId));
      }

  public void initOrgLevel(String value){
     this.initProperty(S_OrgLevel,value);
  }
  public  void setOrgLevel(String value){
     this.set(S_OrgLevel,value);
  }
  public  void setOrgLevelNull(){
     this.set(S_OrgLevel,null);
  }

  public String getOrgLevel(){
       return DataType.getAsString(this.get(S_OrgLevel));
  
  }
  public String getOrgLevelInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgLevel));
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

