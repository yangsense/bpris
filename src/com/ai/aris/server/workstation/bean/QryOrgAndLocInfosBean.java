package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryOrgAndLocInfosBean extends DataContainer implements DataContainerInterface,IQryOrgAndLocInfosValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryOrgAndLocInfos";



  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_OrgName = "ORG_NAME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryOrgAndLocInfosBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOperatorId(long value){
     this.initProperty(S_OperatorId,new Long(value));
  }
  public  void setOperatorId(long value){
     this.set(S_OperatorId,new Long(value));
  }
  public  void setOperatorIdNull(){
     this.set(S_OperatorId,null);
  }

  public long getOperatorId(){
        return DataType.getAsLong(this.get(S_OperatorId));
  
  }
  public long getOperatorIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OperatorId));
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

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
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


 
 }

