package com.ai.aris.server.interfacereal.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

import java.sql.Timestamp;

public class AiscSeverinfoRealBean extends DataContainer implements DataContainerInterface,IAiscSeverinfoRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscSeverinfoReal";



  public final static  String S_ServerRealId = "SERVER_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_OldServerId = "OLD_SERVER_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscSeverinfoRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initServerRealId(long value){
     this.initProperty(S_ServerRealId,new Long(value));
  }
  public  void setServerRealId(long value){
     this.set(S_ServerRealId,new Long(value));
  }
  public  void setServerRealIdNull(){
     this.set(S_ServerRealId,null);
  }

  public long getServerRealId(){
        return DataType.getAsLong(this.get(S_ServerRealId));
  
  }
  public long getServerRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerRealId));
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

  public void initServerId(long value){
     this.initProperty(S_ServerId,new Long(value));
  }
  public  void setServerId(long value){
     this.set(S_ServerId,new Long(value));
  }
  public  void setServerIdNull(){
     this.set(S_ServerId,null);
  }

  public long getServerId(){
        return DataType.getAsLong(this.get(S_ServerId));
  
  }
  public long getServerIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerId));
      }

  public void initOldServerId(long value){
     this.initProperty(S_OldServerId,new Long(value));
  }
  public  void setOldServerId(long value){
     this.set(S_OldServerId,new Long(value));
  }
  public  void setOldServerIdNull(){
     this.set(S_OldServerId,null);
  }

  public long getOldServerId(){
        return DataType.getAsLong(this.get(S_OldServerId));
  
  }
  public long getOldServerIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldServerId));
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

