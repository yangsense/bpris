package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscOrdsubcategoryRealBean extends DataContainer implements DataContainerInterface,IAiscOrdsubcategoryRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscOrdsubcategoryReal";



  public final static  String S_SubcateRealId = "SUBCATE_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_SubcateId = "SUBCATE_ID";
  public final static  String S_OldSubcateId = "OLD_SUBCATE_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOrdsubcategoryRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initSubcateRealId(long value){
     this.initProperty(S_SubcateRealId,new Long(value));
  }
  public  void setSubcateRealId(long value){
     this.set(S_SubcateRealId,new Long(value));
  }
  public  void setSubcateRealIdNull(){
     this.set(S_SubcateRealId,null);
  }

  public long getSubcateRealId(){
        return DataType.getAsLong(this.get(S_SubcateRealId));
  
  }
  public long getSubcateRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SubcateRealId));
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

  public void initSubcateId(long value){
     this.initProperty(S_SubcateId,new Long(value));
  }
  public  void setSubcateId(long value){
     this.set(S_SubcateId,new Long(value));
  }
  public  void setSubcateIdNull(){
     this.set(S_SubcateId,null);
  }

  public long getSubcateId(){
        return DataType.getAsLong(this.get(S_SubcateId));
  
  }
  public long getSubcateIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SubcateId));
      }

  public void initOldSubcateId(long value){
     this.initProperty(S_OldSubcateId,new Long(value));
  }
  public  void setOldSubcateId(long value){
     this.set(S_OldSubcateId,new Long(value));
  }
  public  void setOldSubcateIdNull(){
     this.set(S_OldSubcateId,null);
  }

  public long getOldSubcateId(){
        return DataType.getAsLong(this.get(S_OldSubcateId));
  
  }
  public long getOldSubcateIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldSubcateId));
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

