package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscUser2careprovRealBean extends DataContainer implements DataContainerInterface,IAiscUser2careprovRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscUser2careprovReal";



  public final static  String S_OldUser2careprovId = "OLD_USER2CAREPROV_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_User2careprovId = "USER2CAREPROV_ID";
  public final static  String S_User2careprovRealId = "USER2CAREPROV_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscUser2careprovRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOldUser2careprovId(long value){
     this.initProperty(S_OldUser2careprovId,new Long(value));
  }
  public  void setOldUser2careprovId(long value){
     this.set(S_OldUser2careprovId,new Long(value));
  }
  public  void setOldUser2careprovIdNull(){
     this.set(S_OldUser2careprovId,null);
  }

  public long getOldUser2careprovId(){
        return DataType.getAsLong(this.get(S_OldUser2careprovId));
  
  }
  public long getOldUser2careprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldUser2careprovId));
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

  public void initUser2careprovId(long value){
     this.initProperty(S_User2careprovId,new Long(value));
  }
  public  void setUser2careprovId(long value){
     this.set(S_User2careprovId,new Long(value));
  }
  public  void setUser2careprovIdNull(){
     this.set(S_User2careprovId,null);
  }

  public long getUser2careprovId(){
        return DataType.getAsLong(this.get(S_User2careprovId));
  
  }
  public long getUser2careprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_User2careprovId));
      }

  public void initUser2careprovRealId(long value){
     this.initProperty(S_User2careprovRealId,new Long(value));
  }
  public  void setUser2careprovRealId(long value){
     this.set(S_User2careprovRealId,new Long(value));
  }
  public  void setUser2careprovRealIdNull(){
     this.set(S_User2careprovRealId,null);
  }

  public long getUser2careprovRealId(){
        return DataType.getAsLong(this.get(S_User2careprovRealId));
  
  }
  public long getUser2careprovRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_User2careprovRealId));
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

