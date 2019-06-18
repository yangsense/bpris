package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscBodypartRealBean extends DataContainer implements DataContainerInterface,IAiscBodypartRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscBodypartReal";



  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldBodypartCode = "OLD_BODYPART_CODE";
  public final static  String S_BodypartRealId = "BODYPART_REAL_ID";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscBodypartRealBean() throws AIException{
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

  public void initOldBodypartCode(long value){
     this.initProperty(S_OldBodypartCode,new Long(value));
  }
  public  void setOldBodypartCode(long value){
     this.set(S_OldBodypartCode,new Long(value));
  }
  public  void setOldBodypartCodeNull(){
     this.set(S_OldBodypartCode,null);
  }

  public long getOldBodypartCode(){
        return DataType.getAsLong(this.get(S_OldBodypartCode));
  
  }
  public long getOldBodypartCodeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldBodypartCode));
      }

  public void initBodypartRealId(long value){
     this.initProperty(S_BodypartRealId,new Long(value));
  }
  public  void setBodypartRealId(long value){
     this.set(S_BodypartRealId,new Long(value));
  }
  public  void setBodypartRealIdNull(){
     this.set(S_BodypartRealId,null);
  }

  public long getBodypartRealId(){
        return DataType.getAsLong(this.get(S_BodypartRealId));
  
  }
  public long getBodypartRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_BodypartRealId));
      }

  public void initBodypartCode(long value){
     this.initProperty(S_BodypartCode,new Long(value));
  }
  public  void setBodypartCode(long value){
     this.set(S_BodypartCode,new Long(value));
  }
  public  void setBodypartCodeNull(){
     this.set(S_BodypartCode,null);
  }

  public long getBodypartCode(){
        return DataType.getAsLong(this.get(S_BodypartCode));
  
  }
  public long getBodypartCodeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_BodypartCode));
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

