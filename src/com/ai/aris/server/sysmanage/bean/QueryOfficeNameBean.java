package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QueryOfficeNameBean extends DataContainer implements DataContainerInterface,IQueryOfficeNameValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QueryOfficeName";



  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_CreateTimes = "CREATE_TIMES";
  public final static  String S_State = "STATE";
  public final static  String S_Id = "ID";
  public final static  String S_OfficeName = "OFFICE_NAME";
  public final static  String S_RelationId = "RELATION_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OfficeId = "OFFICE_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryOfficeNameBean() throws AIException{
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

  public void initCreateTimes(Timestamp value){
     this.initProperty(S_CreateTimes,value);
  }
  public  void setCreateTimes(Timestamp value){
     this.set(S_CreateTimes,value);
  }
  public  void setCreateTimesNull(){
     this.set(S_CreateTimes,null);
  }

  public Timestamp getCreateTimes(){
        return DataType.getAsDateTime(this.get(S_CreateTimes));
  
  }
  public Timestamp getCreateTimesInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTimes));
      }

  public void initState(String value){
     this.initProperty(S_State,value);
  }
  public  void setState(String value){
     this.set(S_State,value);
  }
  public  void setStateNull(){
     this.set(S_State,null);
  }

  public String getState(){
       return DataType.getAsString(this.get(S_State));
  
  }
  public String getStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_State));
      }

  public void initId(String value){
     this.initProperty(S_Id,value);
  }
  public  void setId(String value){
     this.set(S_Id,value);
  }
  public  void setIdNull(){
     this.set(S_Id,null);
  }

  public String getId(){
       return DataType.getAsString(this.get(S_Id));
  
  }
  public String getIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Id));
      }

  public void initOfficeName(String value){
     this.initProperty(S_OfficeName,value);
  }
  public  void setOfficeName(String value){
     this.set(S_OfficeName,value);
  }
  public  void setOfficeNameNull(){
     this.set(S_OfficeName,null);
  }

  public String getOfficeName(){
       return DataType.getAsString(this.get(S_OfficeName));
  
  }
  public String getOfficeNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OfficeName));
      }

  public void initRelationId(String value){
     this.initProperty(S_RelationId,value);
  }
  public  void setRelationId(String value){
     this.set(S_RelationId,value);
  }
  public  void setRelationIdNull(){
     this.set(S_RelationId,null);
  }

  public String getRelationId(){
       return DataType.getAsString(this.get(S_RelationId));
  
  }
  public String getRelationIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RelationId));
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

  public void initOfficeId(String value){
     this.initProperty(S_OfficeId,value);
  }
  public  void setOfficeId(String value){
     this.set(S_OfficeId,value);
  }
  public  void setOfficeIdNull(){
     this.set(S_OfficeId,null);
  }

  public String getOfficeId(){
       return DataType.getAsString(this.get(S_OfficeId));
  
  }
  public String getOfficeIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OfficeId));
      }


 
 }

