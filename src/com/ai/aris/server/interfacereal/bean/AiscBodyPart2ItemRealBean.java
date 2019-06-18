package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscBodyPart2ItemRealBean extends DataContainer implements DataContainerInterface,IAiscBodyPart2ItemRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscBodyPart2ItemReal";



  public final static  String S_ItemRealId = "ITEM_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ItemId = "ITEM_ID";
  public final static  String S_OldItemId = "OLD_ITEM_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscBodyPart2ItemRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initItemRealId(long value){
     this.initProperty(S_ItemRealId,new Long(value));
  }
  public  void setItemRealId(long value){
     this.set(S_ItemRealId,new Long(value));
  }
  public  void setItemRealIdNull(){
     this.set(S_ItemRealId,null);
  }

  public long getItemRealId(){
        return DataType.getAsLong(this.get(S_ItemRealId));
  
  }
  public long getItemRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemRealId));
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

  public void initItemId(long value){
     this.initProperty(S_ItemId,new Long(value));
  }
  public  void setItemId(long value){
     this.set(S_ItemId,new Long(value));
  }
  public  void setItemIdNull(){
     this.set(S_ItemId,null);
  }

  public long getItemId(){
        return DataType.getAsLong(this.get(S_ItemId));
  
  }
  public long getItemIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemId));
      }

  public void initOldItemId(long value){
     this.initProperty(S_OldItemId,new Long(value));
  }
  public  void setOldItemId(long value){
     this.set(S_OldItemId,new Long(value));
  }
  public  void setOldItemIdNull(){
     this.set(S_OldItemId,null);
  }

  public long getOldItemId(){
        return DataType.getAsLong(this.get(S_OldItemId));
  
  }
  public long getOldItemIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldItemId));
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

