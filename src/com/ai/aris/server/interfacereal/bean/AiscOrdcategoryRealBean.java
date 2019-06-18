package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscOrdcategoryRealBean extends DataContainer implements DataContainerInterface,IAiscOrdcategoryRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscOrdcategoryReal";



  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrdcategoryRealId = "ORDCATEGORY_REAL_ID";
  public final static  String S_OldOrdcategoryId = "OLD_ORDCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOrdcategoryRealBean() throws AIException{
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

  public void initOrdcategoryId(long value){
     this.initProperty(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryId(long value){
     this.set(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryIdNull(){
     this.set(S_OrdcategoryId,null);
  }

  public long getOrdcategoryId(){
        return DataType.getAsLong(this.get(S_OrdcategoryId));
  
  }
  public long getOrdcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcategoryId));
      }

  public void initOrdcategoryRealId(long value){
     this.initProperty(S_OrdcategoryRealId,new Long(value));
  }
  public  void setOrdcategoryRealId(long value){
     this.set(S_OrdcategoryRealId,new Long(value));
  }
  public  void setOrdcategoryRealIdNull(){
     this.set(S_OrdcategoryRealId,null);
  }

  public long getOrdcategoryRealId(){
        return DataType.getAsLong(this.get(S_OrdcategoryRealId));
  
  }
  public long getOrdcategoryRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcategoryRealId));
      }

  public void initOldOrdcategoryId(long value){
     this.initProperty(S_OldOrdcategoryId,new Long(value));
  }
  public  void setOldOrdcategoryId(long value){
     this.set(S_OldOrdcategoryId,new Long(value));
  }
  public  void setOldOrdcategoryIdNull(){
     this.set(S_OldOrdcategoryId,null);
  }

  public long getOldOrdcategoryId(){
        return DataType.getAsLong(this.get(S_OldOrdcategoryId));
  
  }
  public long getOldOrdcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldOrdcategoryId));
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

